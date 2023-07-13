package com.example.studu_mvc_mvvm_mvi.mvp

/**
 * # 这是一种必内存泄漏的写法
 * > Found 4 objects retained, not dumping heap yet (app is visible & < 5 threshold)
 */
class Presenter(private val view: MVPActivity, private val model: DataModel = DataModel()) {
    fun plus(){
        model.plus{//Presenter->Model
            //Model->Presenter
            view.runOnUiThread{
                view.update(it.toString())//Presenter->View
            }
        }
    }
}