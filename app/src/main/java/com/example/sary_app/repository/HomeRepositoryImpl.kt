package com.example.sary_app.repository

import com.example.sary_app.model.CatalogResult
import com.example.sary_app.network.HomeService
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class HomeRepositoryImpl @Inject constructor(val service: HomeService): Repository {

    override suspend fun getHomeBanner(): List<String> {
        val result = service.callHomeBannerEndpoint()
        return result.result.map { it.image }
    }

    override suspend fun getHomeCatalog(): List<CatalogResult> {
        val data = service.callHomeCatalogEndpoint()
        return data.result
    }


}