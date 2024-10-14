package zawwin.naung.eventcalendar.domain.core

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
