package com.example.sary_app.network


import com.example.sary_app.di.NetworkModule.apiUrl
import com.example.sary_app.model.BannerModel
import com.example.sary_app.model.HomeCatalogModel
import dagger.hilt.android.scopes.ViewModelScoped
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

@ViewModelScoped
class HomeServiceImpl @Inject constructor(val httpClient: HttpClient): HomeService {

    override suspend fun callHomeBannerEndpoint(): BannerModel {
        return httpClient.get<BannerModel> {
            this.apiUrl()
            url(EndPoints.BANNER_URL)
        }
    }


    override suspend fun callHomeCatalogEndpoint(): HomeCatalogModel {
        return httpClient.get<HomeCatalogModel> {
            this.apiUrl()
            url(EndPoints.CATALOG_URL)
        }
    }


}