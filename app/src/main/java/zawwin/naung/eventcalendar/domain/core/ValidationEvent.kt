package zawwin.naung.eventcalendar.domain.core

sealed class ValidationEvent {
    data object Success: ValidationEvent()
}