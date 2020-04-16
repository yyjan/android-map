package com.yun.maskdetector.model

data class StoreSale(
    val code: String,
    val name: String,
    val addr: String,
    val type: String,
    val lat: Float,
    val lng: Float,
    val stock_at: String,
    val remain_stat: String,
    val created_at: String
)