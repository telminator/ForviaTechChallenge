package com.example.challenge.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "apps",
    indices = [Index(value = ["id"], unique = true)]
)
data class AppEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val packageName: String,
    val versionName: String,
    val iconUrl: String,
    val graphicUrl: String?,
    val size: Long,
    val downloads: Int,
    val rating: Float,
    val lastUpdated: Long = System.currentTimeMillis()
)
