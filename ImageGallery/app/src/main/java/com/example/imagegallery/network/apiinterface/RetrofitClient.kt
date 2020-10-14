package com.example.imagegallery.network.apiinterface


import com.example.imagegallery.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object RetrofitClient{
    private var retrofit: Retrofit? = null
    val instance: Retrofit?
        get() {
            if (retrofit == null) {
                val client = OkHttpClient.Builder().addInterceptor(object : Interceptor {
                    @Throws(IOException::class)
                    override
                    fun intercept(chain: Interceptor.Chain): Response? {
                        val newRequest: Request = chain.request().newBuilder()
                            .addHeader("Authorization", "Client-ID 137cda6b5008a7c")
                            .build()
                        return chain.proceed(newRequest)
                    }
                }).build()

                retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            }
            return retrofit
        }
}
