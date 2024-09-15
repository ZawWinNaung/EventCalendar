package com.example.eventcalendar.presentation.home

import android.util.Log
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

    val sdf = SimpleDateFormat("DD/MM/yyyy", Locale.US)

    private val _daysOfThisMonth = MutableStateFlow<MutableList<String>>(mutableListOf())
    val daysOfThisMonth: StateFlow<MutableList<String>> = _daysOfThisMonth

    fun getDaysInMonth(calendar: Calendar = Calendar.getInstance()){
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
}