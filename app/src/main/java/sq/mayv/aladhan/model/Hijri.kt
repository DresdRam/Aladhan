package sq.mayv.aladhan.model

data class Hijri(
    val date: String = "",
    val day: String = "",
    val designation: Designation = Designation(),
    val format: String = "",
    val holidays: List<String> = listOf(),
    val month: Month = Month(),
    val weekday: Weekday = Weekday(),
    val year: String = ""
)