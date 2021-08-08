package com.example.sary_app.model

import com.example.sary_app.utils.Types
import com.example.sary_app.utils.ViewType

data class CatalogUiType(
    val rowCount: Int,
    val data: List<CatalogItem>)


fun CatalogResult.toCatalogType(): ViewType?{
         return when(dataType){
            Types.SMART ->{
                val catalogUiType = CatalogUiType(rowCount , mapCatalogItemWithName())
                ViewType.Smart(catalogUiType)
            }
            Types.GROUP ->{
                val catalogUiType = CatalogUiType(rowCount , mapCatalogItem())
                ViewType.Group(catalogUiType)
            }
            Types.BANNER ->{
                val catalogUiType = CatalogUiType(rowCount , mapCatalogItem())
                ViewType.Banner(catalogUiType)
            }
            else -> null

        }
}


fun CatalogResult.mapCatalogItemWithName(): List<CatalogItem>{
    return data.map { CatalogItem(it.image , it.name) }
}


fun CatalogResult.mapCatalogItem(): List<CatalogItem>{
    return data.map { CatalogItem(it.image , null) }
}
