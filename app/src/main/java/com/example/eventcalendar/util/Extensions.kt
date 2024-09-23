package com.example.eventcalendar.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Long.toTimeString(): String {
    val date = Date(this)
    val formatter = SimpleDateFormat("hh:mm a", Locale.US)
    return try {
        formatter.format(date)
    } catch (e: Throwable) {
        e.printStackTrace()
        ""
    }
}

fun Long.toDateString(): String {
    val formatter = SimpleDateFormat("dd MMM, yyyy", Locale.US)
    return try {
        formatter.format(this)
    } catch (e: Throwable) {
        e.printStackTrace()
        ""
    }
}