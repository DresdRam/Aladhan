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
fun NextPrayerView(
    nextPrayer: NextPrayer,
    viewModel: HomeViewModel
) {
    val remainingTime by viewModel.remainingTime.collectAsState()

    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Next Prayer is ${nextPrayer.prayerReadable}",
        fontSize = 26.sp,
        textAlign = TextAlign.Center,
        color = Color.White
    )

    Text(
        modifier = Modifier.fillMaxWidth(),
        text = nextPrayer.timing,
        fontSize = 38.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )

    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Time remaining $remainingTime",
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}