package com.example.studu_mvc_mvvm_mvi.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.studu_mvc_mvvm_mvi.R

/**
 * # 组成
 * > View<->Presenter<->Model
 * - `Model`：模型层，与MVC中一致，各种处理业务的类，比如数据库CURD。
 * - `View`：显示数据，Activity，`new Presenter(this)`，通过**接口**与Presenter层交互，提供/操作findViewById的一些界面数据。
 * - `Presenter`：业务逻辑处理，持有接口的实现类(**Activity**)、持有Model(`new Model()`)，View层请求先传给Activity再到Presenter，Presenter控制Model进行业务操作,然后操作接口的实现类(**Activity**)。
 * # 优点
 * - View与Model**解耦**。
 * # 缺点
 * -  UI 的操作必须在 Activity 与 Fragment 的生命周期之内，最好在 onStart() 之后 onPause() 之前。
 * -  内存泄漏：常见activity销毁了，presenter层还在请求网络，通过弱引用解决。
 * -  通过接口实现操作（不必要，但是一个Presenter对应多个View时就体现好处了，当然坏处也来了）
 */
class MVPActivity : AppCompatActivity(), IUpdate {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvcactivity)
        val presenter = Presenter(this)
        findViewById<Button>(R.id.button5).setOnClickListener {
            presenter.plus()//View->Presenter
        }
    }

    override fun update(text: String) {
        findViewById<Button>(R.id.button5).text = text
    }
}