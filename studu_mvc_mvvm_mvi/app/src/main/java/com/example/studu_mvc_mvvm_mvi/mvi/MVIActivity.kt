package com.example.studu_mvc_mvvm_mvi.mvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.studu_mvc_mvvm_mvi.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * # MVI组成
 * > View->Intent->Model->View
 * - `Model`：模型层，与MVVM中ViewModel一致，business logic of the application。
 * - `View`：布局，以及布局生命周期控制器(`Activity/Fragment`)。
 * - `Intent`：操作类型
 * # 优点
 * - 强调数据单向流动，很容易对状态变化进行跟踪和回溯。
 * - 通过订阅一个ViewState，省略了MVVM中的那两行模板代码。
 * - **没有内存泄漏**。
 * # 缺点
 * - 所有的操作最终都会转换成State，所以复杂页面的State容易膨胀。
 * - state是不变的，因此每当state需要更新时都要创建新对象，这会带来一定内存开销。
 */
class MVIActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvcactivity)
        val viewModel: ViewModel by viewModels()
        findViewById<Button>(R.id.button5).setOnClickListener {
            lifecycleScope.launch {
                viewModel.dataIntent.send(DataIntent.Click)//View->Intent
            }
        }
        lifecycleScope.launch {
            viewModel.viewState.collect{
                findViewById<Button>(R.id.button5).text = when(it){
                    is ViewState.Inactive -> "InitState"
                    is ViewState.Loading -> "Loading"
                    is ViewState.ResponseData -> it.data
                }//Model->View
            }
        }
    }
}