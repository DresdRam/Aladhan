package sq.mayv.aladhan.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import sq.mayv.aladhan.extension.shimmer

@Composable
fun TimingsViewShimmer() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        repeat(6) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .height(40.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .shimmer()
            )
        }
    }
}