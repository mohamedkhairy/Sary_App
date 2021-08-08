package com.example.sary_app.network

import com.example.sary_app.model.BannerModel
import com.example.sary_app.model.HomeCatalogModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

interface HomeService {

    suspend fun callHomeBannerEndpoint(): BannerModel

    suspend fun callHomeCatalogEndpoint(): HomeCatalogModel

}