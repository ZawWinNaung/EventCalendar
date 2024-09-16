package com.example.eventcalendar.util

import java.text.SimpleDateFormat
import java.util.Calendar

fun isSameDay(cal1: Calendar, cal2: Calendar): Boolean {
    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
            cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
}

fun isWeekEnd(calendar: Calendar) =
    calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY

fun isToday(day: String, sdf: SimpleDateFormat): Boolean {
    val date = sdf.parse(day)
    val cal = Calendar.getInstance()
    cal.time = date
    return isSameDay(Calendar.getInstance(), cal)
}