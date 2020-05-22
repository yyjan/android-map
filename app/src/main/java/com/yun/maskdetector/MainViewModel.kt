package com.yun.maskdetector

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yun.maskdetector.model.StoreSale
import com.yun.maskdetector.repository.StoreRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel constructor(
    private val repository: StoreRepository
) : ViewModel() {
    val stores = MutableLiveData<List<StoreSale>>()

    @SuppressLint("CheckResult")
    fun fetchStores(latitude: Float, longitude: Float, meter: Long) {
        repository.getStoresByGeo(latitude, longitude, meter)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                stores.postValue(it.stores)
            }) {
                it.printStackTrace()
            }
    }
}