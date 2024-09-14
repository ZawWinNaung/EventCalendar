package com.example.eventcalendar.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun DayItem(
    modifier: Modifier = Modifier,
    date: String,
    isSelected: Boolean,
    isToday: Boolean,
    onDateSelected: () -> Unit,
    dateFormatter: SimpleDateFormat = SimpleDateFormat("dd", Locale.US)
) {
    val backgroundColor = when {
        isSelected -> Color.Blue
        isToday -> Color.Green
        else -> Color.LightGray
    }

    val dayNameFormatter = SimpleDateFormat("EE", Locale.US)
    val dateParser = SimpleDateFormat("DD/MM/yyyy", Locale.US)
    val calendar = Calendar.getInstance()

    Column(
        modifier = modifier
            .background(backgroundColor, shape = RoundedCornerShape(8.dp))
            .clickable { onDateSelected() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val parsedDate = dateParser.parse(date)
        parsedDate?.let {
            calendar.time = it
            Log.d("#weeks", dateParser.format(calendar.time))
            Text(text = dayNameFormatter.format(calendar.time))
            BasicText(
                text = dateFormatter.format(calendar.time),
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = if (isSelected || isToday) FontWeight.Bold else FontWeight.Normal,
                    fontSize = 16.sp,
                    color = Color.White
                )
            )
        }

    }
}