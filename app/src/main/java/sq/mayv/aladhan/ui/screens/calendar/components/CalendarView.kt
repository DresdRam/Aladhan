package sq.mayv.aladhan.ui.screens.calendar.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import sq.mayv.aladhan.room.entity.DateEntity

@Composable
fun CalendarView(
    dates: List<DateEntity>,
    onDateClickListener: (Int) -> Unit,
) {
    val weekDays = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
    var weekDaysIndex = 0
    var daysIndex = 0
    val firstDayIndex = weekDays.indexOf(dates[0].weekDay.take(3))
    val cellsCount = firstDayIndex + dates.size

    Column {

        // Calendar WeekDays
        Row {
            repeat(7) {
                WeekDaysCellView(
                    text = weekDays[weekDaysIndex],
                    modifier = Modifier.weight(1f)
                )

                weekDaysIndex++
            }
        }

        // Calendar Month Days
        repeat(6) {
            if (daysIndex >= cellsCount) return@repeat
            Row {
                repeat(7) {

                    if (daysIndex in firstDayIndex until cellsCount) {
                        val day = dates[daysIndex - firstDayIndex].day
                        CalendarCellView(
                            modifier = Modifier.weight(1f),
                            text = day,
                            onClickListener = {
                                onDateClickListener(day.toInt())
                            }
                        )
                    } else {
                        EmptyCellView(
                            modifier = Modifier.weight(1f)
                        )
                    }

                    daysIndex++
                }
            }
        }
    }
}