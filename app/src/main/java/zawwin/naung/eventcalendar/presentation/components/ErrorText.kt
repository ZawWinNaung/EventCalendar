package zawwin.naung.eventcalendar.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import zawwin.naung.eventcalendar.ui.theme.interFontFamily

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