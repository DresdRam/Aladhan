package sq.mayv.aladhan.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
fun TimingsViewShimmer() {
    Column(
        modifier = Modifier
            .padding(top = 15.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Row(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shimmer()
            )

            Box(
                modifier = Modifier
                    .height(40.dp)
                    .padding(horizontal = 15.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .shimmer()
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shimmer()
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            repeat(6) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .fillMaxWidth()
                        .height(30.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .shimmer()
                )
            }
        }
    }
}