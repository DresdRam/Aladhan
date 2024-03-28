package sq.mayv.aladhan.ui.screens.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
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
import sq.mayv.aladhan.ui.screens.home.components.HomeView
import sq.mayv.aladhan.ui.screens.home.components.HomeViewShimmer
import sq.mayv.aladhan.ui.screens.home.viewstate.HomeViewState

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadTimings()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.primary)
    ) {

        Card(
            modifier = Modifier
                .padding(top = 200.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
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
                        HomeViewState.Failure -> {
                            MessageView(
                                message = "Failed to load the timings from the database!",
                                textColor = Color.Red
                            )
                        }

                        HomeViewState.Loading -> {
                            HomeViewShimmer()
                        }

                        is HomeViewState.Success -> {
                            HomeView(it.prayers, it.todayId)
                        }
                    }
                }

            }
        }
    }
}