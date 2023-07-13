package com.example.studu_mvc_mvvm_mvi.mvp

import java.util.concurrent.Executors

class DataModel {
    private val executor = Executors.newSingleThreadExecutor()
    private var count: Int = 0

    fun plus(callback: (Int) -> Unit) = executor.execute {
        Thread.sleep(1000)//LeakCanary是延迟5s,所以连点10来下就会检测得到
        callback(++count)
    }

    fun get() = count.toString()
}