package sq.mayv.aladhan.model

data class Gregorian(
    val date: String = "",
    val day: String = "",
    val designation: Designation = Designation(),
    val format: String = "",
    val month: Month = Month(),
    val weekday: Weekday = Weekday(),
    val year: String = ""
)