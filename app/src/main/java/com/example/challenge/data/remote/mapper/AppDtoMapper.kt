package com.example.challenge.data.remote.mapper

import com.example.challenge.data.local.entity.AppEntity
import com.example.challenge.data.remote.dto.AppDto
import com.example.challenge.domain.model.App

fun AppDto.toDomain(): App {
    return App(
        id = id,
        name = name,
        packageName = packageName,
        versionName = versionName,
        iconUrl = icon,
        graphicUrl = graphic,
        size = size,
        downloads = downloads,
        rating = rating
    )
}

fun AppDto.toEntity(): AppEntity {
    return AppEntity(
        id = id,
        name = name,
        packageName = packageName,
        versionName = versionName,
        iconUrl = icon,
        graphicUrl = graphic,
        size = size,
        downloads = downloads,
        rating = rating
    )
}