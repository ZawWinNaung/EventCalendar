package zawwin.naung.eventcalendar.presentation.create

data class CreateReminderFormState(
    val title: String = "",
    val titleError: String? = null,
    val description: String = "",
    val repeat: String = "",
    val repeatError: String? = null,
    val date: Long = 0,
    val time: Long = 0
)
