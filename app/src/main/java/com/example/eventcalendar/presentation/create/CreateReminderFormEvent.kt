package com.example.eventcalendar.presentation.create

sealed class CreateReminderFormEvent {
    data class TitleChanged(val title: String) : CreateReminderFormEvent()
    data class RepeatChanged(val repeat: String) : CreateReminderFormEvent()
    data object Submit : CreateReminderFormEvent()
}