package com.example.sary_app.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BannerModel(
    @SerialName("result")
    val result: List<Result>,
    @SerialName("status")
    val status: Boolean
)

@Serializable
data class Result(
    @SerialName("branch")
    val branch: List<Int>? = null,
    @SerialName("button_text")
    val buttonText: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("description")
    val description: String,
    @SerialName("expiry_date")
    val expiryDate: String,
    @SerialName("expiry_status")
    val expiryStatus: Boolean,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("is_available")
    val isAvailable: Boolean,
    @SerialName("level")
    val level: String,
    @SerialName("link")
    val link: String,
    @SerialName("photo")
    val photo: String,
    @SerialName("priority")
    val priority: Int,
    @SerialName("start_date")
    val startDate: String,
    @SerialName("title")
    val title: String,
    @SerialName("branches")
    val branches: List<Int>
)