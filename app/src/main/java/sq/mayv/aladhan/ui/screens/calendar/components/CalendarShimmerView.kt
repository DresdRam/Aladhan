package sq.mayv.aladhan.ui.screens.calendar.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import sq.mayv.aladhan.extension.shimmer

@Composable
fun CalendarShimmerView() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(5) {
            Row {
                repeat(7) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .size(40.dp)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .shimmer()
                    )
                }
            }
        }
    }
}