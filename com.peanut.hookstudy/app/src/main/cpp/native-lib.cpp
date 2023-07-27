#include <jni.h>
#include <string>
#include <fstream>
#include <iostream>

using namespace std;

std::string jstring2string(JNIEnv *env, jstring jStr) {
    if (!jStr)
        return "";

    const jclass stringClass = env->GetObjectClass(jStr);
    const jmethodID getBytes = env->GetMethodID(stringClass, "getBytes", "(Ljava/lang/String;)[B");
    const jbyteArray stringJbytes = (jbyteArray) env->CallObjectMethod(jStr, getBytes, env->NewStringUTF("UTF-8"));

    size_t length = (size_t) env->GetArrayLength(stringJbytes);
    jbyte* pBytes = env->GetByteArrayElements(stringJbytes, NULL);

    std::string ret = std::string((char *)pBytes, length);
    env->ReleaseByteArrayElements(stringJbytes, pBytes, JNI_ABORT);

    env->DeleteLocalRef(stringJbytes);
    env->DeleteLocalRef(stringClass);
    return ret;
}

extern "C"
JNIEXPORT jbyteArray JNICALL
Java_com_peanut_hookstudy_CppImpl_readFromJNI(JNIEnv *env, jobject thiz, jstring path) {
    ifstream fl;
    fl.open(jstring2string(env, path));
    fl.seekg(0, ios::end);
    size_t len = fl.tellg();
    char *ret = new char[len];
    fl.seekg(0, ios::beg);
    fl.read(ret, len);
    fl.close();
    jbyteArray result = env->NewByteArray( len);
    env->SetByteArrayRegion( result, 0, len, (const jbyte*)ret );
    delete[] ret;
    return result;
}

//extern "C"
//JNIEXPORT jint JNI_OnLoad(JavaVM* vm, int) {
//
//    return  JNI_VERSION_1_6;
//}

extern "C" long asm_read_file(const char* path, const void* buf, long size);
extern "C"
JNIEXPORT jbyteArray JNICALL
Java_com_peanut_hookstudy_ASMImpl_readFromJNI(JNIEnv *env, jobject thiz, jstring path) {
    char* data;
    data = static_cast<char *>(malloc(1024));
    memset(data, 0, 1024);
    long l = asm_read_file(jstring2string(env, path).c_str(), data, 1024-1);
    if (l < 0) return env->NewByteArray( 0);
    jbyteArray result = env->NewByteArray( l);
    env->SetByteArrayRegion( result, 0, l, (const jbyte*)data );
    delete[] data;
    return result;
}