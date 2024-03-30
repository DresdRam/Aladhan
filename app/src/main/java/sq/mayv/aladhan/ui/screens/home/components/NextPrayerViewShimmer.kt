package sq.mayv.aladhan.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import sq.mayv.aladhan.extension.shimmer

@Composable
fun NextPrayerViewShimmer() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .height(40.dp)
                .width(180.dp)
                .clip(RoundedCornerShape(10.dp))
                .shimmer()
        )

        Box(
            modifier = Modifier
                .height(40.dp)
                .width(200.dp)
                .clip(RoundedCornerShape(10.dp))
                .shimmer()
        )
    }
}