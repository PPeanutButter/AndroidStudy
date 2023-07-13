package com.example.studu_mvc_mvvm_mvi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.studu_mvc_mvvm_mvi.mvc.MVCActivity
import com.example.studu_mvc_mvvm_mvi.mvi.MVIActivity
import com.example.studu_mvc_mvvm_mvi.mvp.MVPActivity
import com.example.studu_mvc_mvvm_mvi.mvvm.MVVMActivity

/**
 * 这个页面是MVC的
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button2).setOnClickListener {
            startActivity(Intent(this, MVCActivity::class.java))
        }
        findViewById<Button>(R.id.button3).setOnClickListener {
            startActivity(Intent(this, MVPActivity::class.java))
        }
        findViewById<Button>(R.id.button).setOnClickListener {
            startActivity(Intent(this, MVVMActivity::class.java))
        }
        findViewById<Button>(R.id.button4).setOnClickListener {
            startActivity(Intent(this, MVIActivity::class.java))
        }
    }
}