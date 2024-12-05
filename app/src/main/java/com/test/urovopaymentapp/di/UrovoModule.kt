package com.test.urovopaymentapp.di

import android.content.Context
import com.test.urovopaymentapp.domain.network.ApiService
import com.test.urovopaymentapp.domain.repository.TradingRepositoryImpl
import com.urovo.i9000s.api.emv.EmvNfcKernelApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(Interceptor { chain->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("accept", "application/json")
                val request = requestBuilder.build()
                chain.proceed(request)
            }).build()
        return Retrofit.Builder().baseUrl(NetworkConstants.BBVA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}

@Module
@InstallIn(SingletonComponent::class)
object UrovoModule {
    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideEmvNfcKernelApi(): EmvNfcKernelApi {
        return EmvNfcKernelApi.getInstance()
    }

    @Provides
    @Singleton
    fun provideTradingCardRepository(apiService: ApiService): TradingRepositoryImpl {
        return TradingRepositoryImpl(apiService)
    }
}
