package sq.mayv.aladhan.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sq.mayv.aladhan.R
import sq.mayv.aladhan.room.entity.TimingsEntity

@Composable
fun TimingsItemView(
    timings: TimingsEntity
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        PagerItemRow(
            icon = R.drawable.ic_fajr,
            prayerReadable = timings.fajrReadable,
            timing = timings.fajr
        )

        PagerItemRow(
            icon = R.drawable.ic_sunrise,
            prayerReadable = timings.sunriseReadable,
            timing = timings.sunrise
        )

        PagerItemRow(
            icon = R.drawable.ic_sun,
            prayerReadable = timings.dhuhrReadable,
            timing = timings.dhuhr
        )

        PagerItemRow(
            icon = R.drawable.ic_sun,
            prayerReadable = timings.asrReadable,
            timing = timings.asr
        )

        PagerItemRow(
            icon = R.drawable.ic_maghrib,
            prayerReadable = timings.maghribReadable,
            timing = timings.maghrib
        )

        PagerItemRow(
            icon = R.drawable.ic_isha,
            prayerReadable = timings.ishaReadable,
            timing = timings.isha
        )
    }
}