package com.example.studu_mvc_mvvm_mvi.mvvm

import kotlinx.coroutines.delay

class DataModel {
    private var count: Int = 0

    suspend fun plus(): DataModel {
        delay(1000)//LeakCanary是延迟5s,所以连点10来下就会检测得到
        count++
        return this
    }

    fun get() = count.toString()
}