package com.example.studu_mvc_mvvm_mvi.mvc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.studu_mvc_mvvm_mvi.R

/**
 * # MVC组成
 * > View->Controller->Model->view。
 * - `Model`：模型层，各种处理业务的类，比如数据库CURD。
 * - `View`：视图层，XML文件。
 * - `Controller`：控制层，Activity。
 * # 优点
 * - 简单方便
 * # 缺点
 * - 万事由Activity完成
 * - 页面复杂导致Activity复杂。
 */
class MVCActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvcactivity)
        val model = DataModel()
        findViewById<Button>(R.id.button5).setOnClickListener { view ->//View->Controller
            model.plus()//Controller->Model
            (view as Button).text = model.get()//Model->view
        }
    }
}