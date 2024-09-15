package com.example.eventcalendar.presentation.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.dt.composedatepicker.CalendarType
import com.dt.composedatepicker.ComposeCalendar
import com.dt.composedatepicker.MonthViewType
import com.dt.composedatepicker.SelectDateListener
import com.example.eventcalendar.presentation.components.DayItem
import com.example.eventcalendar.util.isSameDay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val daysInMonth = viewModel.daysOfThisMonth.collectAsState()

    var selectedDate by remember { mutableStateOf<String?>(null) }

    val listState = rememberLazyListState()

    var currentMonth by remember { mutableStateOf("") }

    val monthPickerShow = remember { mutableStateOf(false) }

    val formatter = SimpleDateFormat("MMM yyyy", Locale.US)

    val selectDateListener = object : SelectDateListener {
        override fun onCanceled() {
            monthPickerShow.value = false
        }

        override fun onDateSelected(date: Date) {
            currentMonth = formatter.format(date.time)
            val calendar = Calendar.getInstance()
            calendar.time = date
            viewModel.getDaysInMonth(calendar)
            monthPickerShow.value = false
        }

    }

    LaunchedEffect(true) {
        viewModel.getDaysInMonth()
        val calendar = Calendar.getInstance()
        currentMonth = formatter.format(calendar.time)
    }

    LaunchedEffect(daysInMonth.value) {
        if (daysInMonth.value.isNotEmpty()) {
            daysInMonth.value.forEachIndexed { index, it ->
                if (isToday(it, viewModel.sdf)) {
                    listState.animateScrollToItem(index)
                }
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            HomeAppBar(
                text = currentMonth,
                onClick = { monthPickerShow.value = true }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            if (daysInMonth.value.isNotEmpty()) {
                LazyRow(
                    contentPadding = PaddingValues(start = 8.dp),
                    state = listState,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    items(daysInMonth.value) { day ->
                        DayItem(
                            modifier = Modifier.padding(end = 8.dp),
                            date = day,
                            isSelected = selectedDate == day,
                            isToday = isToday(day, viewModel.sdf),
                            onDateSelected = {
                                selectedDate = day
                            })
                    }
                }
            }

            HorizontalDivider(modifier = Modifier.padding(top = 16.dp))

            if (monthPickerShow.value) {
                Dialog(
                    onDismissRequest = { monthPickerShow.value = false }
                ) {
                    Box(modifier = Modifier.padding(bottom = 360.dp)) {
                        ComposeCalendar(
                            locale = Locale.US,
                            title = "",
                            listener = selectDateListener,
                            calendarType = CalendarType.MONTH_AND_YEAR,
                            themeColor = MaterialTheme.colorScheme.primary,
                            unselectedColor = MaterialTheme.colorScheme.secondary,
                            negativeButtonTitle = "CANCEL",
                            positiveButtonTitle = "OK",
                            monthViewType = MonthViewType.ONLY_MONTH
                        )
                    }
                }
            }

        }
    }
}

fun isToday(day: String, sdf: SimpleDateFormat): Boolean {
    val date = sdf.parse(day)
    val cal = Calendar.getInstance()
    cal.time = date
    return isSameDay(Calendar.getInstance(), cal)
}