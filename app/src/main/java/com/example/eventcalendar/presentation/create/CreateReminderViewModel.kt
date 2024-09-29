package com.example.eventcalendar.presentation.create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventcalendar.domain.core.MyResult
import com.example.eventcalendar.domain.core.ValidationEvent
import com.example.eventcalendar.domain.entity.MyEventEntity
import com.example.eventcalendar.domain.usecase.InsertEvent
import com.example.eventcalendar.domain.validation.ValidateEventTitle
import com.example.eventcalendar.util.stringToDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class CreateReminderViewModel @Inject constructor(
    private val insertEvent: InsertEvent,
    private val validateEventTitle: ValidateEventTitle
) : ViewModel() {

    var formState by mutableStateOf(CreateReminderFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvent = validationEventChannel.receiveAsFlow()

    fun onEvent(event: CreateReminderFormEvent) {
        when (event) {
            is CreateReminderFormEvent.TitleChanged -> {
                formState = formState.copy(title = event.title, titleError = null)
            }

            is CreateReminderFormEvent.RepeatChanged -> {
                formState = formState.copy(repeat = event.repeat, repeatError = null)
            }

            is CreateReminderFormEvent.Submit -> {
                validateData()
            }
        }
    }

    private fun validateData() {
        val titleResult = validateEventTitle.execute(formState.title)
        val hasError = listOf(titleResult).any { !it.successful }
        if (hasError) {
            formState = formState.copy(
                titleError = titleResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    fun insert(date: String, onSuccess:() -> Unit) {
        val d = date.stringToDate()
        val calendar = Calendar.getInstance()
        val sampleText: (words: Int) -> String =
            { words -> LoremIpsum(words = words).values.first() }
        viewModelScope.launch {
            val event = MyEventEntity(
                id = 0,
                title = formState.title,
                description = sampleText(24),
                time = calendar.time.time,
                endTime = null,
                date = d.time,
                endDate = null,
                repeat = true,
                repeatPattern = null
            )
            val insert = insertEvent.execute(event)
            when (insert) {
                is MyResult.Success -> {
                    onSuccess()
                }

                is MyResult.Error -> {

                }
            }
        }
    }
}