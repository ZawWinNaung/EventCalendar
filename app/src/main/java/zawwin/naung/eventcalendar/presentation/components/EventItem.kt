package zawwin.naung.eventcalendar.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import zawwin.naung.eventcalendar.domain.constant.EventBackgroundColors
import zawwin.naung.eventcalendar.domain.constant.EventTextColors
import zawwin.naung.eventcalendar.domain.model.MyEvent
import zawwin.naung.eventcalendar.ui.theme.interFontFamily
import zawwin.naung.eventcalendar.ui.theme.poppinsFontFamily
import zawwin.naung.eventcalendar.util.toDateString
import zawwin.naung.eventcalendar.util.toTimeString

@Composable
fun EventItem(
    event: MyEvent
) {
    val startTime = event.time.toTimeString()
    val date = event.date.toDateString()
    var endTime = ""
    var backgroundColor: Color = EventBackgroundColors.SimpleEvent.color
    var textColor: Color = EventTextColors.SimpleEvent.color
    when (event) {
        is MyEvent.SimpleEvent -> {
            backgroundColor = EventBackgroundColors.SimpleEvent.color
            textColor = EventTextColors.SimpleEvent.color
            endTime = event.endTime.toTimeString()
        }

        is MyEvent.Reminder -> {
            backgroundColor = EventBackgroundColors.Reminder.color
            textColor = EventTextColors.Reminder.color
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = startTime,
            style = MaterialTheme.typography.labelLarge,
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Bold,
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()

                .padding(start = 8.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardColors(
                contentColor = MaterialTheme.colorScheme.onBackground,
                containerColor = backgroundColor,
                disabledContentColor = MaterialTheme.colorScheme.onBackground,
                disabledContainerColor = backgroundColor
            ),
            onClick = {}
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2,
                    minLines = 2,
                    text = event.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = textColor,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                )
                when (event) {
                    is MyEvent.Reminder -> {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier,
                                text = startTime,
                                style = MaterialTheme.typography.bodyLarge,
                                color = textColor,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.SemiBold,
                            )

                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 8.dp),
                                text = date,
                                style = MaterialTheme.typography.bodyLarge,
                                color = textColor,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Start
                            )
                        }
                    }

                    else -> {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = startTime,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = textColor,
                                    fontFamily = interFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                )
                                Text(
                                    text = "Start",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = textColor,
                                    textAlign = TextAlign.End,
                                    fontFamily = poppinsFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .weight(0.5f)
                                    .background(
                                        color = textColor,
                                        shape = RoundedCornerShape(20.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    modifier = Modifier.padding(vertical = 8.dp),
                                    text = "40m",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = backgroundColor,
                                    fontFamily = poppinsFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                )
                            }
                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = endTime,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = textColor,
                                    textAlign = TextAlign.End,
                                    fontFamily = interFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                )
                                Text(
                                    text = "End",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = textColor,
                                    textAlign = TextAlign.Center,
                                    fontFamily = poppinsFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                )
                            }
                        }
                    }
                }
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = event.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = textColor,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                )

            }
        }

    }
}