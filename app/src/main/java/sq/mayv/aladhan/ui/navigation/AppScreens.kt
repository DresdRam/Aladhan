package sq.mayv.aladhan.ui.navigation

enum class AppScreens {
    HomeScreen,
    CalendarScreen;

    companion object {
        fun route(screen: AppScreens): String {
            return screen.name
        }
    }

}