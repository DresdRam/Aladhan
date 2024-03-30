package sq.mayv.aladhan.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomePrayerShimmerView(){
    Column(
        modifier = Modifier
            .padding(top = 15.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {

        DateIndicatorShimmerView()

        TimingsViewShimmer()
    }
}