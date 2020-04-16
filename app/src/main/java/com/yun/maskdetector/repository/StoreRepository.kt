package com.yun.maskdetector.repository

import com.yun.maskdetector.model.StoreSaleResult
import com.yun.maskdetector.remote.StoreApi
import io.reactivex.Single

class StoreRepository(private val storeApi: StoreApi) {
    fun getStoresByGeo(
        latitude: Float,
        longitude: Float,
        meter: Long
    ): Single<StoreSaleResult> =
        storeApi.getStoresByGeo(latitude, longitude, meter)
}

