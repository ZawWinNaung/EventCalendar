package com.example.eventcalendar.presentation.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.material.icons.rounded.Repeat
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eventcalendar.domain.constant.RepeatType
import com.example.eventcalendar.domain.constant.sdf
import com.example.eventcalendar.domain.core.ValidationEvent
import com.example.eventcalendar.presentation.components.DatePickerModal
import com.example.eventcalendar.presentation.components.ErrorText
import com.example.eventcalendar.ui.theme.interFontFamily
import com.example.eventcalendar.ui.theme.poppinsFontFamily
import com.example.eventcalendar.util.longToDate
import com.example.eventcalendar.util.stringToDate
import com.example.eventcalendar.util.toDateString
import com.example.eventcalendar.util.toTimeString
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateReminderScreen(
    sheetState: SheetState,
    initialDate: String,
    onDismissRequest: () -> Unit,
    onSaveSuccess: (date: String) -> Unit,
    viewModel: CreateReminderViewModel = hiltViewModel()
) {
    var expanded by remember { mutableStateOf(false) }
    var datePickerState by remember { mutableStateOf(false) }
    val maxChar = 140

    val repeatTypes = enumValues<RepeatType>()

    val state = viewModel.formState
    val context = LocalContext.current

    val dateFieldInteractionSource = remember {
        object : MutableInteractionSource {
            override val interactions = MutableSharedFlow<Interaction>(
                extraBufferCapacity = 16,
                onBufferOverflow = BufferOverflow.DROP_OLDEST,
            )

            override suspend fun emit(interaction: Interaction) {
                when (interaction) {
                    is PressInteraction.Press -> {
                        datePickerState = true
                    }
                }
            }

            override fun tryEmit(interaction: Interaction): Boolean {
                return interactions.tryEmit(interaction)
            }
        }
    }

    LaunchedEffect(context) {
        viewModel.validationEvent.collect { event ->
            when (event) {
                ValidationEvent.Success -> {
                    viewModel.insert {
                        viewModel.formState = CreateReminderFormState()
                        val formattedDate = sdf.format(state.date.longToDate())
                        onSaveSuccess(formattedDate)
                    }
                }
            }
        }
    }

    LaunchedEffect(true) {
        val calendar = Calendar.getInstance()
        viewModel.onEvent(CreateReminderFormEvent.TimeChanged(calendar.time.time))
        val parsedDate = sdf.parse(initialDate)
        parsedDate?.let {
            viewModel.onEvent(CreateReminderFormEvent.DateChanged(it.time))
        }
    }

    ModalBottomSheet(
        modifier = Modifier.fillMaxHeight(),
        sheetState = sheetState,
        shape = BottomSheetDefaults.HiddenShape,
        onDismissRequest = {
            viewModel.formState = CreateReminderFormState()
            onDismissRequest()
        },
        dragHandle = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (datePickerState) {
                    DatePickerModal(
                        onDateSelected = { date ->
                            date?.let {
                                viewModel.onEvent(CreateReminderFormEvent.DateChanged(it))
                            }
                        },
                        onDismiss = { datePickerState = false }
                    )
                }
                BottomSheetDefaults.DragHandle()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Cancel", fontFamily = interFontFamily,
                        color = MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        modifier = Modifier.clickable {
                            viewModel.formState = CreateReminderFormState()
                            onDismissRequest()
                        }
                    )
                    Text(
                        "New Reminder", fontFamily = poppinsFontFamily,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                    Text(
                        "Save", fontFamily = interFontFamily,
                        color = Color.Blue,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        modifier = Modifier.clickable {
                            viewModel.onEvent(CreateReminderFormEvent.Submit)
                        }
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(
                        "Reminder Title",
                        fontFamily = interFontFamily,
                        color = Color.Gray
                    )
                },
                value = state.title,
                shape = RoundedCornerShape(10.dp),
                onValueChange = { viewModel.onEvent(CreateReminderFormEvent.TitleChanged(it)) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor = Color.LightGray,
                    disabledBorderColor = Color.LightGray
                ),
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = interFontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                isError = state.titleError != null
            )
            if (state.titleError != null) {
                ErrorText(state.titleError)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(end = 4.dp),
                    value = state.time.toTimeString(),
                    prefix = {
                        Icon(
                            imageVector = Icons.Rounded.AccessTime,
                            contentDescription = "time",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    },
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = interFontFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    shape = RoundedCornerShape(10.dp),
                    readOnly = true,
                    onValueChange = { },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor = Color.LightGray,
                        disabledBorderColor = Color.LightGray,
                    ),
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 4.dp),
                    value = state.date.toDateString(),
                    prefix = {
                        Icon(
                            imageVector = Icons.Rounded.CalendarToday,
                            contentDescription = "calendar",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    },
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = interFontFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    shape = RoundedCornerShape(10.dp),
                    readOnly = true,
                    onValueChange = { },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor = Color.LightGray,
                        disabledBorderColor = Color.LightGray,
                    ),
                    interactionSource = dateFieldInteractionSource
                )
            }
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 4.dp),
                    value = state.repeat,
                    readOnly = true,
                    placeholder = {
                        Text(
                            "Repeat",
                            fontFamily = interFontFamily,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    },
                    prefix = {
                        Icon(
                            imageVector = Icons.Rounded.Repeat,
                            contentDescription = "calendar",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    },
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = interFontFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = { },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor = Color.LightGray,
                        disabledBorderColor = Color.LightGray,
                        disabledTextColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSurface,
                        focusedTrailingIconColor = MaterialTheme.colorScheme.onSurface,
                    ),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    isError = state.repeatError != null
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    repeatTypes.forEach {
                        DropdownMenuItem(
                            text = { Text(text = it.type, fontFamily = interFontFamily) },
                            onClick = {
                                viewModel.onEvent(CreateReminderFormEvent.RepeatChanged(it.type))
                                expanded = false
                            }
                        )
                    }
                }
            }
            if (state.repeatError != null) {
                ErrorText(state.repeatError)
            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 16.dp),
                placeholder = {
                    Text(
                        "Description",
                        color = Color.Gray,
                        fontFamily = interFontFamily
                    )
                },
                value = state.description,
                minLines = 3,
                shape = RoundedCornerShape(10.dp),
                onValueChange = { viewModel.onEvent(CreateReminderFormEvent.DescriptionChanged(it)) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor = Color.LightGray,
                    disabledBorderColor = Color.LightGray
                ),
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = interFontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                ),
                prefix = {
                    Icon(
                        imageVector = Icons.Rounded.EditNote,
                        contentDescription = "calendar",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                },
                supportingText = {
                    Text(
                        text = "${state.description.length} / $maxChar",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                        fontFamily = interFontFamily
                    )
                },
            )
        }
    }
}