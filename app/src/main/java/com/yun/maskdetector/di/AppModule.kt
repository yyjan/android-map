package com.yun.maskdetector.di

import com.yun.maskdetector.MainViewModel
import com.yun.maskdetector.remote.StoreApi
import com.yun.maskdetector.remote.provideLoggingInterceptor
import com.yun.maskdetector.remote.provideOkHttpClient
import com.yun.maskdetector.remote.provideRetrofit
import com.yun.maskdetector.repository.StoreRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModule = module {
    single { StoreRepository(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val networkModule = module {
    factory { provideLoggingInterceptor() }
    factory { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { storeApi(get()) }
}

fun storeApi(retrofit: Retrofit): StoreApi = retrofit.create(StoreApi::class.java)
