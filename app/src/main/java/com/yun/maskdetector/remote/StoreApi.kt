package com.yun.maskdetector.remote

import com.yun.maskdetector.BuildConfig
import com.yun.maskdetector.model.StoreSaleResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface StoreApi {

    @GET("${BuildConfig.API_VERSION}/storesByGeo/json")
    fun getStoresByGeo(
        @Query("lat") latitude: Float,
        @Query("lng") longitude: Float,
        @Query("m") meter: Long
    ): Single<StoreSaleResult>
}