package com.example.eventcalendar.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eventcalendar.presentation.components.WeekItem
import java.util.Calendar

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val daysInMonth = viewModel.nearByWeeks.collectAsState()

    var selectedDate by remember { mutableStateOf<String?>(null) }

    val listState = rememberLazyListState()

    LaunchedEffect(true) {
        viewModel.getCurrentMonth()
    }

    LaunchedEffect(daysInMonth.value) {
        daysInMonth.value.forEachIndexed { index, calendar ->

        }
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (daysInMonth.value.isNotEmpty()) {
                LazyRow(
                    contentPadding = PaddingValues(start = 8.dp),
                    state = listState,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    items(daysInMonth.value) { week ->
                        WeekItem(modifier = Modifier.fillParentMaxWidth(),
                            week = week,
                            selectedDate = selectedDate,
                            onSelectedDate = { index ->
                                selectedDate = week.days[index]
                            }
                        )
                    }
                }
            }

        }
    }
}