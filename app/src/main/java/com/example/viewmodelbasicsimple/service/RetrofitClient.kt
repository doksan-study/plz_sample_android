package com.example.viewmodelbasicsimple.service

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://run.mocky.io"

    private val interceptorClient = OkHttpClient().newBuilder().addInterceptor(RequestInterceptor())
        .addInterceptor(ResponseInterceptor()).build()

//    private val interceptorClient = OkHttpClient().newBuilder().addInterceptor(LoggingInterceptor()).build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(interceptorClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}
const val TAG = "RETROFIT_CLIENT_TAG"
/**
 * Sample For OkHttp Interceptors
 * From. https://square.github.io/okhttp/features/interceptors/
 */

class LoggingInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val t1 = System.nanoTime()
        Log.d(TAG, "Sending request ${request.url()} on ${chain.connection()} \n${request.headers()} ")

        val response = chain.proceed(request)

        val t2 = System.nanoTime()
        Log.d(TAG, "Received response for ${response.request().url()} in ${(t2 - t1)/1e6} \n${response.headers()}")

        return response
    }
}

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        var cookie = ""

        builder.addHeader("cookie", cookie)
        builder.addHeader("User-Agent", "Plz-Sample-App")

        return chain.proceed(builder.build())
    }
}

class ResponseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        when (response.code()) {
            400 -> {
                // todo Control Error
            }
            401 -> {
                // todo Control Error

            }
            402 -> {
                // todo Control Error

            }
        }

        return response
    }
}