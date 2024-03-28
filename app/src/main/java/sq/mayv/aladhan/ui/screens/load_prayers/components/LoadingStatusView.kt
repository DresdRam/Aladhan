package sq.mayv.aladhan.ui.screens.load_prayers.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import sq.mayv.aladhan.components.LoadingMessageView
import sq.mayv.aladhan.components.MessageView
import sq.mayv.aladhan.ui.navigation.AppScreens
import sq.mayv.aladhan.ui.screens.load_prayers.LoadPrayersViewModel
import sq.mayv.aladhan.ui.screens.load_prayers.viewstate.LoadPrayersViewState

@Composable
fun LoadingStatusView(
    navController: NavController,
    viewModel: LoadPrayersViewModel
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.getMonthlyPrayers()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        AnimatedContent(
            targetState = viewState,
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
                LoadPrayersViewState.Loading -> {
                    LoadingMessageView(
                        message = "Downloading prayer timings from the server.\nPlease be patient."
                    )
                }

                LoadPrayersViewState.Stored -> {
                    navController.navigate(AppScreens.route(AppScreens.HomeScreen)) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }

                is LoadPrayersViewState.Loaded -> {
                    LaunchedEffect(key1 = Unit) {
                        viewModel.storeTimings()
                    }

                    MessageView(
                        message = "Storing prayer timings locally.\nMight take a few seconds."
                    )
                }

                is LoadPrayersViewState.Failure -> {
                    MessageView(
                        message = it.message
                    )
                }
            }
        }
    }
}