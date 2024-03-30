package sq.mayv.aladhan.model

data class NextPrayer(
    val nextPrayerExist: Boolean = true,
    val prayerReadable: String = "",
    val timing: String = ""
)
