package com.example.teststrarwars.di

import android.content.Context
import androidx.room.Room
import com.example.teststrarwars.api.ApiService
import com.example.teststrarwars.api.ApiService.Companion.BASE_URL
import com.example.teststrarwars.data.local.PeopleRoomDatabase
import com.example.teststrarwars.data.local.FavoritePeopleDao
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCharterDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            PeopleRoomDatabase::class.java,
            "charter_database")
            .build()

    @Provides
    @Singleton
    fun provideCharterDao(appDataBase: PeopleRoomDatabase): FavoritePeopleDao {
        return appDataBase.getFavoritePeopleDao()
    }

    @Provides
    fun moshi() = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    fun loggin() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okHttpClient() = OkHttpClient.Builder().addInterceptor(loggin()).build()


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi()))
            .client(okHttpClient())
            .build()

    @Provides
    @Singleton
    fun providePeopleApi(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

}