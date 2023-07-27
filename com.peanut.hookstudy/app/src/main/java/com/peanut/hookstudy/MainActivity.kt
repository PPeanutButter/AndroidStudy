package com.peanut.hookstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        File(this.filesDir.path+"/"+"CMakeLists.txt").writeText(assets.open("CMakeLists.txt").readBytes().decodeToString())
        val r = ASMImpl.readString(this.filesDir.path+"/"+"CMakeLists.txt")
        findViewById<TextView>(R.id.textView).text = r
    }

    companion object {
        init {
            System.loadLibrary("ReadFile")
        }
    }
}