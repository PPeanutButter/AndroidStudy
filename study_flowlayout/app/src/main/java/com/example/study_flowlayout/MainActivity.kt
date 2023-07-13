package com.example.study_flowlayout

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.textView).setOnClickListener {
            findViewById<FlowLayout>(R.id.flow).addView(TextView(this).apply {
                this.text = Array((Math.random() * 20 + 1).toInt()){"_"}.joinToString(separator = "")
                this.id = ++this@MainActivity.id
                this.setBackgroundColor((0xFF000000 or Random.nextLong(0x00ffffff)).toInt())
            })
        }
    }
}