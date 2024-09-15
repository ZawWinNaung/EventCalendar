package com.example.eventcalendar.presentation.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventcalendar.util.isWeekEnd
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
        isSelected -> MaterialTheme.colorScheme.tertiaryContainer
        isToday -> MaterialTheme.colorScheme.primaryContainer
        else -> MaterialTheme.colorScheme.background
    }

    val dayNameFormatter = SimpleDateFormat("EE", Locale.US)
    val dateParser = SimpleDateFormat("DD/MM/yyyy", Locale.US)

    Card(
        onClick = { onDateSelected() },
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = modifier
            .width(60.dp)
            .height(80.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        shape = RoundedCornerShape(20.dp),
        colors = CardColors(
            containerColor = backgroundColor,
            contentColor = MaterialTheme.colorScheme.onBackground,
            disabledContentColor = MaterialTheme.colorScheme.onBackground,
            disabledContainerColor = MaterialTheme.colorScheme.background
        ),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            val dateString = dateParser.parse(date)
            dateString?.let {
                val calendar = Calendar.getInstance()
                calendar.time = it
                val isWeekEnd = isWeekEnd(calendar)
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = dayNameFormatter.format(it.time).uppercase(Locale.US),
                    style = MaterialTheme.typography.bodySmall.copy(
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = if (isWeekEnd) Color.Red else MaterialTheme.colorScheme.onSurface,
                    )
                )
                BasicText(
                    text = dateFormatter.format(it.time),
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = if (isSelected || isToday) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                )
            }


        }
    }
}