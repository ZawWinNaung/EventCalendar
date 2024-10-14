package zawwin.naung.eventcalendar.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import zawwin.naung.eventcalendar.domain.constant.sdf
import zawwin.naung.eventcalendar.domain.core.MyResult
import zawwin.naung.eventcalendar.domain.model.MyEvent
import zawwin.naung.eventcalendar.domain.usecase.GetEventsByDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getEventsByDate: GetEventsByDate
) : ViewModel() {

    private val _daysOfThisMonth = MutableStateFlow<MutableList<String>>(mutableListOf())
    val daysOfThisMonth: StateFlow<MutableList<String>> = _daysOfThisMonth

    private val _eventList = MutableStateFlow<List<MyEvent>>(listOf())
    val eventList: StateFlow<List<MyEvent>> = _eventList

    fun getDaysInMonth(calendar: Calendar = Calendar.getInstance()) {
        val daysList = mutableListOf<String>()

        // Set the calendar to the first day of the month
        calendar.set(Calendar.DAY_OF_MONTH, 1)

        // Get the number of days in the current month
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        // Iterate through each day in the month
        for (day in 1..daysInMonth) {
            calendar.set(Calendar.DAY_OF_MONTH, day)
            val date = sdf.format(calendar.time)
            daysList.add(date)
        }

        _daysOfThisMonth.value = daysList
    }



    fun getEventsByDate(date: String) {
        val d = sdf.parse(date)
        viewModelScope.launch {
            when (val getEventList = getEventsByDate.execute(d.time)) {
                is MyResult.Success -> {
                    _eventList.value = getEventList.data
                }

                is MyResult.Error -> {

                }
            }
        }
    }
}