package sq.mayv.aladhan.ui.screens.load_prayers

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import sq.mayv.aladhan.MainActivity
import sq.mayv.aladhan.components.MessageView
import sq.mayv.aladhan.components.PermissionDialog
import sq.mayv.aladhan.data.PermissionEvent
import sq.mayv.aladhan.extension.openAppSettings
import sq.mayv.aladhan.extension.permissionsAreGranted
import sq.mayv.aladhan.ui.screens.load_prayers.components.LoadingStatusView
import sq.mayv.aladhan.ui.screens.load_prayers.viewstate.PermissionViewState
import sq.mayv.aladhan.util.LocationPermissionTextProvider

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LoadPrayersScreen(
    navController: NavHostController,
    viewModel: LoadPrayersViewModel = hiltViewModel()
) {

    val context = LocalContext.current as MainActivity
    val permissions = listOf(
        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
    )

    val permissionState = rememberMultiplePermissionsState(
        permissions = permissions
    )

    val settingsResults =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult(),
            onResult = {
                if (permissionState.allPermissionsGranted) {
                    viewModel.handleViewState(PermissionEvent.Granted)
                }
            }
        )

    LaunchedEffect(!context.permissionsAreGranted(permissions)) {
        permissionState.launchMultiplePermissionRequest()
    }

    val permissionViewState by viewModel.permissionViewState.collectAsStateWithLifecycle()

    when {
        permissionState.allPermissionsGranted -> {
            LaunchedEffect(Unit) {
                viewModel.handleViewState(PermissionEvent.Granted)
            }
        }

        permissionState.shouldShowRationale -> {
            PermissionDialog(
                permissionTextProvider = LocationPermissionTextProvider(),
                onDismiss = {},
                onOkClick = {
                    permissionState.launchMultiplePermissionRequest()
                }
            )
        }

        !permissionState.allPermissionsGranted && !permissionState.shouldShowRationale -> {
            LaunchedEffect(Unit) {
                viewModel.handleViewState(PermissionEvent.Revoked)
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        AnimatedContent(
            targetState = permissionViewState,
            label = "ViewState Animation",
            transitionSpec = {
                fadeIn(
                    animationSpec = tween(600, easing = EaseIn)
                ).togetherWith(
                    fadeOut(
                        animationSpec = tween(600, easing = EaseOut)
                    )
                )
            }) {
            when (it) {
                PermissionViewState.CheckingPermissions -> {
                    Box(
                        Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(80.dp)
                                .align(Alignment.Center),
                            strokeWidth = 5.dp
                        )
                    }
                }

                PermissionViewState.RevokedPermissions -> {
                    MessageView(
                        message = "Aladhan needs the location permissions.",
                        buttonEnabled = true,
                        buttonMessage = "Allow Permissions",
                        onButtonClick = { context.openAppSettings(settingsResults) }
                    )
                }

                PermissionViewState.GrantedPermissions -> {
                    LoadingStatusView(
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}