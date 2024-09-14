package com.example.eventcalendar.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.eventcalendar.domain.model.Week
import com.example.eventcalendar.util.isSameDay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun WeekItem(
    modifier: Modifier = Modifier,
    week: Week,
    selectedDate: String? = "",
    onSelectedDate: (index: Int) -> Unit
) {
    val currentDate = Calendar.getInstance()

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        DayItem(
            modifier = Modifier
                .padding(end = 8.dp)
                .fillMaxWidth()
                .weight(1f),
            date = week.days[0],
            isSelected = selectedDate == week.days[0],
            isToday = isSameDay(currentDate, getCalendarFromDate(week.days[0])),
            onDateSelected = { onSelectedDate(0) })

        DayItem(
            modifier = Modifier
                .padding(end = 8.dp)
                .fillMaxWidth()
                .weight(1f),
            date = week.days[1],
            isSelected = selectedDate == week.days[1],
            isToday = isSameDay(currentDate, getCalendarFromDate(week.days[1])),
            onDateSelected = { /*TODO*/ })

        DayItem(
            modifier = Modifier
                .padding(end = 8.dp)
                .fillMaxWidth()
                .weight(1f),
            date = week.days[2],
            isSelected = selectedDate == week.days[2],
            isToday = isSameDay(currentDate, getCalendarFromDate(week.days[2])),
            onDateSelected = { /*TODO*/ })

        DayItem(
            modifier = Modifier
                .padding(end = 8.dp)
                .fillMaxWidth()
                .weight(1f),
            date = week.days[3],
            isSelected = selectedDate == week.days[3],
            isToday = isSameDay(currentDate, getCalendarFromDate(week.days[3])),
            onDateSelected = { /*TODO*/ })

        DayItem(
            modifier = Modifier
                .padding(end = 8.dp)
                .fillMaxWidth()
                .weight(1f),
            date = week.days[4],
            isSelected = selectedDate == week.days[4],
            isToday = isSameDay(currentDate, getCalendarFromDate(week.days[4])),
            onDateSelected = { /*TODO*/ })

        DayItem(
            modifier = Modifier
                .padding(end = 8.dp)
                .fillMaxWidth()
                .weight(1f),
            date = week.days[5],
            isSelected = selectedDate == week.days[5],
            isToday = isSameDay(currentDate, getCalendarFromDate(week.days[5])),
            onDateSelected = { /*TODO*/ })

        DayItem(
            modifier = Modifier
                .padding(end = 8.dp)
                .fillMaxWidth()
                .weight(1f),
            date = week.days[6],
            isSelected = selectedDate == week.days[6],
            isToday = isSameDay(currentDate, getCalendarFromDate(week.days[6])),
            onDateSelected = { /*TODO*/ })
    }
}

fun getCalendarFromDate(dateString: String): Calendar {
    val parser = SimpleDateFormat("DD/MM/yyyy", Locale.US)
    val date = parser.parse(dateString)
    val calendar = Calendar.getInstance()
    date?.let {
        calendar.time = it
    }
    return calendar
}