package com.yun.maskdetector

import androidx.lifecycle.ViewModel
import com.yun.maskdetector.repository.AppRepository

class MainViewModel(
    private val repository: AppRepository
) : ViewModel() {

    fun fetchData() = repository.fetchData()
}