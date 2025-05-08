package com.example.challenge.data.local.mapper

import com.example.challenge.data.local.entity.AppEntity
import com.example.challenge.domain.model.App

fun AppEntity.toDomain(): App {
    return App(
        id = id,
        name = name,
        packageName = packageName,
        versionName = versionName,
        iconUrl = iconUrl,
        graphicUrl = graphicUrl,
        size = size,
        downloads = downloads,
        rating = rating
    )
}

fun App.toEntity(): AppEntity {
    return AppEntity(
        id = id,
        name = name,
        packageName = packageName,
        versionName = versionName,
        iconUrl = iconUrl,
        graphicUrl = graphicUrl,
        size = size,
        downloads = downloads,
        rating = rating
    )
}