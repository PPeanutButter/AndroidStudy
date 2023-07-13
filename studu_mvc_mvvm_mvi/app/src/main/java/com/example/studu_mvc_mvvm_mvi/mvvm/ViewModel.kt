package com.example.studu_mvc_mvvm_mvi.mvvm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class ViewModel: ViewModel() {
    private val mutex = Mutex()
    private val dataModel: DataModel = DataModel()

    /**
     * 每一个操作都需要这两个变量，是否有像proto之类的脚本？
     * 通过val countState:T直接生成以下代码？
     */
    private val _countState = MutableStateFlow("0")
    val countState: StateFlow<String> = _countState.asStateFlow()

    suspend fun plus(){
        withContext(Dispatchers.IO){
            mutex.withLock{//排队机制
                dataModel.plus()//ViewModel->Model
                _countState.update {
                    dataModel.get()//Model->ViewModel
                }
            }
        }
    }
}