package com.example.eventcalendar.presentation.home

import androidx.lifecycle.ViewModel
import com.example.eventcalendar.domain.model.Week
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _nearByWeeks = MutableStateFlow<List<Week>>(emptyList())
    val nearByWeeks: StateFlow<List<Week>> = _nearByWeeks

    fun getCurrentMonth() {
        _nearByWeeks.value = getDaysInMonth()
    }

    fun getDaysInMonth(): List<Week> {
        val calendar = Calendar.getInstance()

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)

        // Get current week days
        val currentWeekDays = getWeekDays(calendar)

        // Get last week days (subtract 7 days)
        calendar.add(Calendar.WEEK_OF_YEAR, -1)
        val lastWeekDays = getWeekDays(calendar)

        // Get next week days (move forward by 2 weeks from last week)
        calendar.add(Calendar.WEEK_OF_YEAR, 2)
        val nextWeekDays = getWeekDays(calendar)

        val weekList: List<Week> =
            listOf(Week(lastWeekDays), Week(currentWeekDays), Week(nextWeekDays))


        return weekList
    }

    fun getWeekDays(startCalendar: Calendar): List<String> {
        val daysList = mutableListOf<String>()
        val tempCalendar = startCalendar.clone() as Calendar
        val sdf = SimpleDateFormat("DD/MM/yyyy", Locale.US)
        repeat(7) {
            daysList.add(sdf.format(tempCalendar.time))
            tempCalendar.add(Calendar.DAY_OF_YEAR, 1)

        }
        return daysList
    }
}