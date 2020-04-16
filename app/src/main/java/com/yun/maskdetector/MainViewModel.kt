package com.yun.maskdetector
import androidx.lifecycle.ViewModel
import com.yun.maskdetector.repository.StoreRepository

class MainViewModel constructor(
    private val repository: StoreRepository
) : ViewModel()
