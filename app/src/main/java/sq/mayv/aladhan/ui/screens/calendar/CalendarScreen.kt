package sq.mayv.aladhan.ui.screens.calendar

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import sq.mayv.aladhan.R
import sq.mayv.aladhan.ui.screens.calendar.components.CalendarView
import sq.mayv.aladhan.ui.screens.calendar.viewstate.CalendarTimingViewState
import sq.mayv.aladhan.ui.screens.calendar.viewstate.CalendarViewState
import sq.mayv.aladhan.ui.screens.home.components.TimingsItemView
import java.util.Calendar

@Composable
fun CalendarScreen(
    navController: NavHostController,
    viewModel: CalendarViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.loadDates()
    }
    val calendar = Calendar.getInstance()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val timingsViewState by viewModel.timingsViewState.collectAsStateWithLifecycle()
    var selectedDay by remember { mutableStateOf(calendar.get(Calendar.DAY_OF_MONTH)) }

    LaunchedEffect(key1 = selectedDay) {
        viewModel.loadClickedDateTimings(day = selectedDay)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                IconButton(
                    modifier = Modifier.align(Alignment.CenterStart),
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back_arrow),
                        contentDescription = ""
                    )
                }
            }
        },
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            AnimatedContent(targetState = viewState, label = "") { state ->
                when (state) {
                    CalendarViewState.Failure -> {}
                    CalendarViewState.Loading -> {}
                    is CalendarViewState.Success -> {
                        CalendarView(dates = state.dates) { day ->
                            if (selectedDay != day) {
                                selectedDay = day
                            }
                        }
                    }
                }
            }

            AnimatedContent(targetState = timingsViewState, label = "") { state ->
                when (state) {
                    CalendarTimingViewState.Failure -> {}
                    CalendarTimingViewState.Loading -> {}
                    is CalendarTimingViewState.Success -> {
                        TimingsItemView(timings = state.timings)
                    }
                }
            }
        }
    }
}