package sq.mayv.aladhan.model

data class Gregorian(
    val date: String = "",
    val format: String = "",
    val day: String = "",
    val month: Month = Month(),
    val year: String = "",
    val weekday: Weekday = Weekday()
)