package sq.mayv.aladhan.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.formatToCalendarDay(): String = SimpleDateFormat("d", Locale.getDefault()).format(this)