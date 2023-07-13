package com.example.studu_mvc_mvvm_mvi.mvi

class DataModel {
    private var count: Int = 0

    fun plus() = count++

    fun get() = count.toString()
}