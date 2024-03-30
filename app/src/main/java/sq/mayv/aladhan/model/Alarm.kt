package sq.mayv.aladhan.model

import java.time.LocalDateTime

data class Alarm(
    val time: LocalDateTime,
    val message: String
)
