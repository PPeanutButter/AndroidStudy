package com.example.studu_mvc_mvvm_mvi.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class ViewModel(private val repository: DataModel = DataModel()): ViewModel() {
    val viewState = MutableStateFlow<ViewState>(ViewState.Inactive)
    val dataIntent = Channel<DataIntent>(Channel.UNLIMITED)
    private val mutex = Mutex()

    init {
        viewModelScope.launch {
            dataIntent.consumeAsFlow().collect{
                when(it){
                    DataIntent.Click -> fetchData()//Intent->ViewModel
                }
            }
        }
    }

    private fun fetchData(){
        viewModelScope.launch {
            mutex.withLock {
                viewState.value = ViewState.Loading
                delay(500)
                repository.plus()
                viewState.value = ViewState.ResponseData(repository.get())
                delay(500)//给一点显示时间
            }
        }
    }
}

enum class DataIntent{
    Click
}