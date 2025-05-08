package com.example.challenge.domain.model

data class App(
    val id: Int,
    val name: String,
    val packageName: String,
    val versionName: String,
    val iconUrl: String,
    val graphicUrl: String?,
    val size: Long,
    val downloads: Int,
    val rating: Float
)