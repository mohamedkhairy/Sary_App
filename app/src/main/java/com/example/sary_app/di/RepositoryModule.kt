package com.example.sary_app.di

import com.example.sary_app.network.HomeService
import com.example.sary_app.network.HomeServiceImpl
import com.example.sary_app.repository.HomeRepositoryImpl
import com.example.sary_app.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideHomeRepository(service: HomeService): Repository {
        return HomeRepositoryImpl(service)
    }

    @Singleton
    @Provides
    fun provideHeroService(httpClient: HttpClient): HomeService {
        return HomeServiceImpl(httpClient)
    }


}






