package com.example.mytestapp.api


import com.example.mytestapp.data.model.CompanyModel
import com.example.mytestapp.data.model.DeliveryModel
import com.example.mytestapp.data.model.FilterModel
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path

object CompaniesApi {
    private const val BASE_URL: String = "https://stage.mister.am/public-api/"
    const val CITY_ID: Int = 1

    val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    interface ApiInterface {

        @GET("companies/{id}")
        fun getCompaniesList(@Path("id") cityId: Int):
                Single<List<CompanyModel>>

        @GET("city/{id}")
        fun getAvailableDeliveryData(@Path("id") cityId: Int):
                Single<DeliveryModel>

        @GET("city/{id}/filters")
        fun getFiltersList(@Path("id") cityId: Int):
                Single<List<FilterModel>>
    }
}