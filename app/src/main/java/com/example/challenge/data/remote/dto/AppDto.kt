package com.example.challenge.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ApiResponseDto(
    @SerializedName("status") val status: String,
    @SerializedName("responses") val responses: ResponsesDto
)

data class ResponsesDto(
    @SerializedName("listApps") val listApps: ListAppsDto
)

data class ListAppsDto(
    @SerializedName("info") val info: InfoDto, @SerializedName("datasets") val datasets: DatasetsDto
)

data class DatasetsDto(
    @SerializedName("all") val all: AllDto
)

data class AllDto(
    @SerializedName("info") val info: InfoDto, @SerializedName("data") val data: DataDto
)

data class InfoDto(
    @SerializedName("status") val status: String, @SerializedName("time") val time: TimeDto
)

data class TimeDto(
    @SerializedName("seconds") val seconds: Double, @SerializedName("human") val human: String
)

data class DataDto(
    @SerializedName("total") val total: Int,
    @SerializedName("offset") val offset: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("next") val next: Int,
    @SerializedName("hidden") val hidden: Int,
    @SerializedName("list") val list: List<AppDto>
)

data class AppDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("package") val packageName: String,
    @SerializedName("store_id") val storeId: Int,
    @SerializedName("store_name") val storeName: String,
    @SerializedName("vername") val versionName: String,
    @SerializedName("vercode") val versionCode: Int,
    @SerializedName("md5sum") val md5Sum: String,
    @SerializedName("apk_tags") val apkTags: List<String>?,
    @SerializedName("size") val size: Long,
    @SerializedName("downloads") val downloads: Int,
    @SerializedName("pdownloads") val pDownloads: Int,
    @SerializedName("added") val added: String,
    @SerializedName("modified") val modified: String,
    @SerializedName("updated") val updated: String,
    @SerializedName("uptype") val updateType: String,
    @SerializedName("rating") val rating: Float,
    @SerializedName("icon") val icon: String,
    @SerializedName("graphic") val graphic: String?
)



