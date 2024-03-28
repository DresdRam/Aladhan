package sq.mayv.aladhan.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(startDestination: String = AppScreens.route(AppScreens.HomeScreen)) {
    val navigationController = rememberNavController()
    val transitionSpeed = 300

    NavHost(navController = navigationController, startDestination = startDestination) {

        composable(
            route = AppScreens.route(AppScreens.HomeScreen),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }, popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }) {
            //HomeScreen(navController = navigationController)
        }

        composable(
            route = AppScreens.route(AppScreens.CalendarScreen),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }, popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }) {
            //CalendarScreen(navController = navigationController)
        }
    }
}