package sq.mayv.aladhan.ui.screens.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import sq.mayv.aladhan.R
import sq.mayv.aladhan.components.MessageView
import sq.mayv.aladhan.components.RoundedButton
import sq.mayv.aladhan.ui.navigation.AppScreens
import sq.mayv.aladhan.ui.screens.home.components.NextPrayerView
import sq.mayv.aladhan.ui.screens.home.components.NextPrayerViewShimmer
import sq.mayv.aladhan.ui.screens.home.components.NoNextPrayerView
import sq.mayv.aladhan.ui.screens.home.components.TimingsView
import sq.mayv.aladhan.ui.screens.home.components.TimingsViewShimmer
import sq.mayv.aladhan.ui.screens.home.viewstate.NextPrayerViewState
import sq.mayv.aladhan.ui.screens.home.viewstate.TimingsViewState

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val timingsViewState by viewModel.timingsViewState.collectAsStateWithLifecycle()
    val nextPrayerViewState by viewModel.nextPrayerViewState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadTimings()
        viewModel.loadNextPrayer()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.primary)
    ) {

        AnimatedContent(
            targetState = nextPrayerViewState,
            label = "ViewState Animation",
            transitionSpec = {
                fadeIn(
                    animationSpec = tween(600, easing = EaseIn)
                ).togetherWith(
                    fadeOut(
                        animationSpec = tween(600, easing = EaseOut)
                    )
                )
            }) { state ->

            Column(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                when (state) {
                    NextPrayerViewState.Loading -> {
                        NextPrayerViewShimmer()
                    }

                    NextPrayerViewState.NoNextPrayer -> {
                        NoNextPrayerView()
                    }

                    is NextPrayerViewState.Success -> {
                        NextPrayerView(
                            nextPrayer = state.nextPrayer,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }

        Card(
            modifier = Modifier
                .padding(top = 200.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {

            AnimatedContent(
                targetState = timingsViewState,
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
                    TimingsViewState.Failure -> {
                        MessageView(
                            message = "Failed to load the timings from the database!",
                            textColor = Color.Red
                        )
                    }

                    TimingsViewState.Loading -> {
                        TimingsViewShimmer()
                    }

                    is TimingsViewState.Success -> {
                        TimingsView(
                            prayers = it.prayers,
                            indexOfToday = it.indexOfToday
                        )
                    }
                }
            }

            RoundedButton(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .padding(horizontal = 25.dp)
                    .fillMaxWidth(),
                text = "Calendar",
                onClicked = {
                    navController.navigate(AppScreens.route(AppScreens.CalendarScreen)) {
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}