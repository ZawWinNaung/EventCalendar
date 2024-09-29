package com.example.eventcalendar.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.eventcalendar.ui.theme.interFontFamily
import java.lang.Error

@Composable
fun ErrorText(error: String) {
    Text(
        text = error,
        color = MaterialTheme.colorScheme.error,
        fontFamily = interFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold
    )
}