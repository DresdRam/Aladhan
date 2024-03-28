package sq.mayv.aladhan.model

data class Date(
    val gregorian: Gregorian = Gregorian(),
    val hijri: Hijri = Hijri(),
    val readable: String = "",
    val timestamp: String = ""
)