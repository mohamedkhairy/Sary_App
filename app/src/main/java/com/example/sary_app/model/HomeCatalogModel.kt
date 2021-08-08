package com.example.sary_app.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeCatalogModel(
    @SerialName("message")
    val message: String,
    @SerialName("other")
    val other: Other,
    @SerialName("result")
    val result: List<CatalogResult>,
    @SerialName("status")
    val status: Boolean
)

@Serializable
data class Other(
    @SerialName("business_status")
    val businessStatus: BusinessStatus,
    @SerialName("show_special_order_view")
    val showSpecialOrderView: Boolean,
    @SerialName("uncompleted_profile_settings")
    val uncompletedProfileSettings: UncompletedProfileSettings
)

@Serializable
data class CatalogResult(
    @SerialName("data")
    val data: List<Data>,
    @SerialName("data_type")
    val dataType: String,
    @SerialName("id")
    val id: Int,
    @SerialName("row_count")
    val rowCount: Int,
    @SerialName("show_title")
    val showTitle: Boolean,
    @SerialName("title")
    val title: String,
    @SerialName("ui_type")
    val uiType: String
)

@Serializable
data class BusinessStatus(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String
)

@Serializable
data class UncompletedProfileSettings(
    @SerialName("image")
    val image: String,
    @SerialName("is_completed_profile")
    val isCompletedProfile: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("show_tag")
    val showTag: Boolean
)

@Serializable
data class Data(
//    @SerialName("deep_link")
//    val deepLink: String,
//    @SerialName("empty_content_image")
//    val emptyContentImage: String,
//    @SerialName("empty_content_message")
//    val emptyContentMessage: String,
//    @SerialName("filters")
//    val filters: List<Filter>,
//    @SerialName("group_id")
//    val groupId: Int,
//    @SerialName("has_data")
//    val hasData: Boolean,
    @SerialName("image")
    val image: String,
    @SerialName("name")
    val name: String? = null,
//    @SerialName("show_in_brochure_link")
//    val showInBrochureLink: Boolean,
//    @SerialName("show_unavailable_items")
//    val showUnavailableItems: Boolean
)

//@Serializable
//data class Filter(
//    @SerialName("filter_id")
//    val filterId: Int,
//    @SerialName("name")
//    val name: String
//)