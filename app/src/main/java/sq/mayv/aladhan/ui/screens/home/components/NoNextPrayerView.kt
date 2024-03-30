package sq.mayv.aladhan.ui.screens.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import sq.mayv.aladhan.model.NextPrayer
import sq.mayv.aladhan.ui.screens.home.HomeViewModel

@Composable
fun NoNextPrayerView() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "You Did All Prayers Today",
        textAlign = TextAlign.Center,
        color = Color.White
    )

    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "May Allah Accept Your Prayers",
        textAlign = TextAlign.Center,
        color = Color.White
    )
}