package com.example.eventcalendar.domain.constant

import androidx.compose.ui.graphics.Color

enum class EventBackgroundColors(val color: Color) {
    Reminder(Color(0xFFC39BD3)),
    SimpleEvent(Color(0xFFF8C471))
}

enum class EventTextColors(val color: Color) {
    Reminder(Color(0xFF512E5F)),
    SimpleEvent(Color(0xFF784212))
}