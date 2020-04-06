package com.yun.maskdetector.di

import com.yun.maskdetector.MainViewModel
import com.yun.maskdetector.repository.AppRepository
import com.yun.maskdetector.repository.AppRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<AppRepository> { AppRepositoryImpl() }
    viewModel { MainViewModel(get()) }
}