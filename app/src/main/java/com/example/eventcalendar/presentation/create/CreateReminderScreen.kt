package com.example.eventcalendar.presentation.create

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Card
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventcalendar.ui.theme.interFontFamily
import com.example.eventcalendar.ui.theme.poppinsFontFamily
import com.example.eventcalendar.util.toDateString
import com.example.eventcalendar.util.toTimeString
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateReminderScreen(sheetState: SheetState, onDismissRequest: () -> Unit) {
    var titleValue by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var repeat by remember { mutableStateOf("") }
    val maxChar = 140

    val repeatTypes = listOf("Never", "Everyday", "Every Week", "Every Month", "Every Year")

    LaunchedEffect(true) {
        val calendar = Calendar.getInstance()
        time = calendar.time.time.toTimeString()
        date = calendar.time.time.toDateString()
    }

    ModalBottomSheet(
        modifier = Modifier.fillMaxHeight(),
        sheetState = sheetState,
        shape = BottomSheetDefaults.HiddenShape,
        onDismissRequest = onDismissRequest,
        dragHandle = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                value = titleValue,
                shape = RoundedCornerShape(10.dp),
                onValueChange = { titleValue = it },
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
            )
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
                    value = time,
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
                    )
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 4.dp),
                    value = date,
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
                    )
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
                    value = repeat,
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
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    repeatTypes.forEach {
                        DropdownMenuItem(
                            text = { Text(text = it, fontFamily = interFontFamily) },
                            onClick = {
                                repeat = it
                                expanded = false
                            }
                        )
                    }
                }
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
                value = description,
                minLines = 3,
                shape = RoundedCornerShape(10.dp),
                onValueChange = { description = it },
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
                        text = "${description.length} / $maxChar",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                        fontFamily = interFontFamily
                    )
                },
            )
        }
    }
}