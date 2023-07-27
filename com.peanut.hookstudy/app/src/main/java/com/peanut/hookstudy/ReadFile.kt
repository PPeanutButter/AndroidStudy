package com.peanut.hookstudy

import java.io.File

interface ReadFile {
    fun read(path: String):ByteArray
    fun readString(path: String):String{
        return read(path = path).decodeToString()
    }
}

/**
 * Java实现
 */
object JavaImpl: ReadFile{
    override fun read(path: String): ByteArray {
        return File(path).readBytes()
    }
}

/**
 * C++实现
 */
object CppImpl: ReadFile{
    private external fun readFromJNI(path: String): ByteArray
    override fun read(path: String): ByteArray {
        return readFromJNI(path)
    }
}

/**
 * 汇编实现
 */
object ASMImpl: ReadFile{
    private external fun readFromJNI(path: String): ByteArray
    override fun read(path: String): ByteArray {
        return readFromJNI(path)
    }
}