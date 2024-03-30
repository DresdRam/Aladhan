package sq.mayv.aladhan.ui.screens.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import sq.mayv.aladhan.room.entity.relation.DateWithTimings

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TimingsPager(
    pagerState: PagerState,
    prayers: List<DateWithTimings>
) {
    HorizontalPager(
        modifier = Modifier
            .fillMaxWidth(),
        state = pagerState,
        outOfBoundsPageCount = 1,
        userScrollEnabled = false
    ) { position ->
        TimingsItemView(timings = prayers[position].timings)
    }
}