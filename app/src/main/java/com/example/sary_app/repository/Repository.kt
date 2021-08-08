package com.example.sary_app.repository

import com.example.sary_app.model.CatalogResult


interface Repository {
    suspend fun getHomeBanner(): List<String>
    suspend fun getHomeCatalog(): List<CatalogResult>
}