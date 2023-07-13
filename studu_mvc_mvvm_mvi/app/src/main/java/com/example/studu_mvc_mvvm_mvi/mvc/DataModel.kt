package com.example.studu_mvc_mvvm_mvi.mvc

class DataModel {
    private var count: Int = 0

    fun plus() = count++

    fun get() = count.toString()
}