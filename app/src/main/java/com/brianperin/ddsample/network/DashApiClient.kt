package com.brianperin.ddsample.network

import com.brianperin.ddsample.util.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Our Api client for dispatching requests for basic header manipulation
 * or if we wanted to add auth
 */
object DashApiClient {

    private var endpoint: String? = null

    fun setEndpoint(endpoint: String) {
        this.endpoint = endpoint
    }

    public val dashService: DashService by lazy {

        val logging = HttpLoggingInterceptor()

        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)

            .addInterceptor { chain ->
                val original = chain.request()
                val builder = original.newBuilder()

                builder.header(Constants.ACCEPT, Constants.APPLICATION_JSON)

                builder.method(original.method(), original.body())

                val originalRequest = chain.proceed(builder.build())
                val newResponse = originalRequest.newBuilder()

                newResponse.build()
            }
            .build()

        Retrofit.Builder()
            .baseUrl(endpoint)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(DashService::class.java)
    }


}