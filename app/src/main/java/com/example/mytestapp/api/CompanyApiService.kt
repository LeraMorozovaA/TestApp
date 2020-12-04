package com.example.mytestapp.api

import com.example.mytestapp.data.model.CompanyModel
import com.example.mytestapp.data.model.DeliveryModel
import com.example.mytestapp.data.model.FilterModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CompanyApiService {

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