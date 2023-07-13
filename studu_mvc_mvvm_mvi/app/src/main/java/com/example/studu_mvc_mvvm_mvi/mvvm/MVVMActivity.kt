package com.example.studu_mvc_mvvm_mvi.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.studu_mvc_mvvm_mvi.R
import kotlinx.coroutines.launch


/**
 * # MVVM组成
 * > View<->ViewModel<->Model
 * - `Model`：模型层，与MVC中一致，各种处理业务的类，比如数据库CURD。
 * - `View`：布局，以及布局生命周期控制器(`Activity/Fragment`)，View层请求先传给Activity再到ViewModel，ViewModel操作完Model之后通过setValue传递事件到View(Activity有liveData.name.observe(callback))。
 * - `ViewModel`：与`Presenter`大致相同，但不持有View层的任何引用。(通过MutableStateFlow而不是接口，研究下和LiveData的区别？没有区别，但是Flow是kotlin！协程保证了及时销毁)
 * # 优点
 * - 核心思想是观察者模式，耦合度更低，复用性更强，**没有内存泄漏**。
 * # 缺点
 * - ViewModel与View层的通信变得**更加困难**了。
 */
class MVVMActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvcactivity)
        //val viewModel: ViewModel = ViewModelProvider(this)[ViewModel::class.java]
        val viewModel: ViewModel by viewModels()
        findViewById<Button>(R.id.button5).setOnClickListener {
            lifecycleScope.launch {
                viewModel.plus()//View->ViewModel
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.countState.collect {
                    findViewById<Button>(R.id.button5).text = it//ViewModel->View
                }
            }
        }
    }
}