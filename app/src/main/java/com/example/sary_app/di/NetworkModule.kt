package com.example.sary_app.di

import com.example.sary_app.network.Header
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun client(): HttpClient {
        return HttpClient(Android) {
                install(JsonFeature) {
                    serializer = KotlinxSerializer(
                        kotlinx.serialization.json.Json  {
                            ignoreUnknownKeys = true // if the server sends extra fields, ignore them
                        }
                    )
                }
                install(Logging){
                    logger = Logger.DEFAULT
                    level = LogLevel.ALL
                }
            }

    }

    fun HttpRequestBuilder.apiUrl() {
        headers {
            append(Header.DEVICE_TYPE_KEY , Header.DeviceType)
            append(HttpHeaders.Authorization, Header.Authorization)
            append(Header.ACCEPT_LANGUAGE_KEY, Header.AcceptLanguage)
            append(Header.Platform , Header.Flagship)
            append(Header.APP_VERSION_KEY, Header.AppVersion)
        }
    }
}