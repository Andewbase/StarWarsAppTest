package com.example.teststrarwars.di

import android.content.Context
import androidx.room.Room
import com.example.teststrarwars.util.BASE_URL
import com.example.teststrarwars.data.retrofit.api.ApiService
import com.example.teststrarwars.data.room.PeopleRoomDatabase
import com.example.teststrarwars.data.room.dao.PeopleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun baseUrl() = BASE_URL

    @Provides
    fun loggin() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okHttpClient() = OkHttpClient.Builder().addInterceptor(loggin()).build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): ApiService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()
            .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideCharterDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            PeopleRoomDatabase::class.java,
            "charter_database")
            .build()

    @Provides
    fun provideCharterDao(appDataBase: PeopleRoomDatabase): PeopleDao {
        return appDataBase.getPeopleDao()
    }
}