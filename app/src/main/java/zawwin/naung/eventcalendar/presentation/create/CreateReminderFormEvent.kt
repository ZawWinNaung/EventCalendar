package zawwin.naung.eventcalendar.presentation.create

sealed class CreateReminderFormEvent {
    data class TitleChanged(val title: String) : CreateReminderFormEvent()
    data class DateChanged(val date: Long) : CreateReminderFormEvent()
    data class TimeChanged(val time: Long) : CreateReminderFormEvent()
    data class RepeatChanged(val repeat: String) : CreateReminderFormEvent()
    data class DescriptionChanged(val description: String) : CreateReminderFormEvent()
    data object Submit : CreateReminderFormEvent()
}