package sq.mayv.aladhan.ui.screens.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun PagerItemRow(
    @DrawableRes icon: Int,
    prayerReadable: String,
    timing: String
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint= Color.Unspecified
        )

        Box(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterStart),
                text = prayerReadable,
                color = Color.Black
            )
        }
        Box {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = timing,
                color = Color.Black
            )
        }
    }
}