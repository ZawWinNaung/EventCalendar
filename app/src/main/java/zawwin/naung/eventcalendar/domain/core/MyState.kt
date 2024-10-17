package zawwin.naung.eventcalendar.domain.core

sealed class MyState {
    data object Initial : MyState()
    data object Loading : MyState()
    data object Success : MyState()
    data class Error(val message: Exception) : MyState()
}