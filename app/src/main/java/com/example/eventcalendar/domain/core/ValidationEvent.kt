package com.example.eventcalendar.domain.core

sealed class ValidationEvent {
    data object Success: ValidationEvent()
}