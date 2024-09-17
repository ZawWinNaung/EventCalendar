package com.example.eventcalendar.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_event")
data class MyEventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val time: Long,
    val endTime: Long?,
    val date: Long,
    val endDate: Long?,
    val repeat: Boolean,
    val repeatPattern: String?
)