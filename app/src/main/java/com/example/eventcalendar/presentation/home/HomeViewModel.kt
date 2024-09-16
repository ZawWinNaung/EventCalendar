package com.example.eventcalendar.presentation.home

import android.util.Log
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.lifecycle.ViewModel
import com.example.eventcalendar.domain.model.MyEvent
import com.example.eventcalendar.util.isToday
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    val sdf = SimpleDateFormat("DD/MM/yyyy", Locale.US)

    private val _daysOfThisMonth = MutableStateFlow<MutableList<String>>(mutableListOf())
    val daysOfThisMonth: StateFlow<MutableList<String>> = _daysOfThisMonth

    private val _todayEvents = MutableStateFlow<MutableList<MyEvent>>(mutableListOf())
    val todayEvents: StateFlow<MutableList<MyEvent>> = _todayEvents

    fun getDaysInMonth(calendar: Calendar = Calendar.getInstance()) {
        val daysList = mutableListOf<String>()

        // Set the calendar to the first day of the month
        calendar.set(Calendar.DAY_OF_MONTH, 1)

        // Get the number of days in the current month
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        // Iterate through each day in the month
        for (day in 1..daysInMonth) {
            calendar.set(Calendar.DAY_OF_MONTH, day)
            val date = sdf.format(calendar.time)
            daysList.add(date)
        }

        _daysOfThisMonth.value = daysList
    }

    fun getTodayEvents(todayDate: String) {
        val today = sdf.parse(todayDate)

        val todayEventList: MutableList<MyEvent> = mutableListOf()
        val calendar = Calendar.getInstance()
        if (isToday(todayDate, sdf)) {
            val sampleText = LoremIpsum(words = 100).values.first()
            todayEventList.addAll(
                listOf(
                    MyEvent.Reminder(
                        repeat = "",
                        title = sampleText,
                        description = sampleText,
                        time = calendar.time.time
                    ),
                    MyEvent.Reminder(
                        repeat = "",
                        title = "Call to David",
                        description = "ask for overtime",
                        time = calendar.time.time
                    ),
                    MyEvent.SimpleEvent(
                        repeat = "",
                        title = "Join technical meeting",
                        description = "buy coffee before",
                        time = calendar.time.time,
                        endTime = calendar.time.time
                    ),
                    MyEvent.SimpleEvent(
                        repeat = "",
                        title = sampleText,
                        description = sampleText,
                        time = calendar.time.time,
                        endTime = calendar.time.time
                    ),
                )
            )
            _todayEvents.value = todayEventList
        } else {
            _todayEvents.value = todayEventList
        }
    }
}