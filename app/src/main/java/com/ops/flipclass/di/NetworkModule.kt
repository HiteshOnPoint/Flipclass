package com.ops.flipclass.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.ops.flipclass.utilities.SharedPrefConstants
import com.ops.flipclass.utilities.SharedPrefsUtils
import com.saint.saintfood.api.WebService
import com.saint.saintfood.repository.ApiRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(WebService.BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .addConverterFactory((GsonConverterFactory.create()))
            .client(getOkHttpClient(androidContext()))
            .build()
            .create(WebService::class.java)
    }

    single {
        ApiRepository(get())
    }
}

fun getOkHttpClient(context: Context): OkHttpClient {
    val cacheSize = (10 * 1024 * 1024).toLong()
    val myCache = Cache(context.cacheDir, cacheSize)
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    //interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
    return OkHttpClient.Builder()
        .cache(myCache)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(2, TimeUnit.MINUTES)
        .writeTimeout(10, TimeUnit.MINUTES)
        .addInterceptor { chain ->
            val request = chain.request()
            if (isNetworkAvailable(context)!!) {

                request.newBuilder().addHeader("Accept", "application/json;charset=utf-8")
                    .addHeader("Content-Type", "application/json")
                    .addHeader(
                        "Authorization",
                        "Bearer " + SharedPrefsUtils.getStringPreference(
                            context,
                            SharedPrefConstants.ACCESS_TOKEN
                        )
                    )
                    .addHeader("Cache-Control", "public, max-age=" + 10)
                    .build()
            } else {
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 10
                ).build()
            }
            chain.proceed(request)
        }
        .addInterceptor(interceptor)
        .build()
}

fun isNetworkAvailable(context: Context): Boolean? {
    var isConnected: Boolean? = false // Initial Value
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}
