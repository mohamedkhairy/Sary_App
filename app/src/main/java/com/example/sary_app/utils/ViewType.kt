package com.example.sary_app.utils

import com.example.sary_app.model.CatalogUiType

sealed class ViewType {
    data class Smart(val view: CatalogUiType) : ViewType()
    data class Group(val view: CatalogUiType) : ViewType()
    data class Banner(val view: CatalogUiType) : ViewType()
}

object Types{
    const val SMART = "smart"
    const val GROUP = "group"
    const val BANNER = "banner"
}