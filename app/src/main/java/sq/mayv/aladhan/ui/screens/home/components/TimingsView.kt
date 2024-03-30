package sq.mayv.aladhan.ui.screens.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import sq.mayv.aladhan.room.entity.relation.DateWithTimings

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TimingsView(
    prayers: List<DateWithTimings>,
    indexOfToday: Int
) {

    var currentIndex by remember { mutableStateOf(if (indexOfToday != -1) indexOfToday else 0) }
    val pagerState = rememberPagerState(initialPage = currentIndex, pageCount = { prayers.size })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(top = 15.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {

        DateIndicator(
            date = prayers[currentIndex].date.readableDate,
            onPreviousClick = {
                if (currentIndex != 0) {
                    currentIndex--

                    coroutineScope.launch {
                        pagerState.animateScrollToPage(currentIndex)
                    }
                }
            },
            onNextClick = {
                if (currentIndex != prayers.lastIndex) {
                    currentIndex++

                    coroutineScope.launch {
                        pagerState.animateScrollToPage(currentIndex)
                    }
                }
            }
        )

        TimingsPager(
            pagerState = pagerState,
            prayers = prayers
        )
    }
}