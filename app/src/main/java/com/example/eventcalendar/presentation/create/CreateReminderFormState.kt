package com.example.eventcalendar.presentation.create

data class CreateReminderFormState(
    val title: String = "",
    val titleError: String? = null,
    val description: String = "",
    val repeat: String = "",
    val repeatError: String? = null
)
