package com.dapascript.wings.di

import android.content.Context
import androidx.viewbinding.BuildConfig
import com.dapascript.wings.data.pref.DataPreference
import com.dapascript.wings.data.remote.network.ApiService
import com.dapascript.wings.data.repository.WingsRepository
import com.dapascript.wings.data.repository.WingsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.CipherSuite
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val baseUrl = "http://192.168.1.13/wings/"
        val spec = listOf(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS)
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        if (!BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectionSpecs(spec)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideWingsRepository(apiService: ApiService): WingsRepository {
        return WingsRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideDataPreference(@ApplicationContext context: Context): DataPreference {
        return DataPreference(context)
    }
}