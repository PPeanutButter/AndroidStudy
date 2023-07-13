package com.example.studu_mvc_mvvm_mvi.mvi

sealed class ViewState {
    object Inactive : ViewState()
    object Loading : ViewState()
    data class ResponseData(val data: String) : ViewState()
}