#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_id_rezzz_gtpspnativekit_MainActivity_nativeStatus(JNIEnv* env, jobject) {
    const std::string msg =
        "Native C++ bridge: loaded successfully.\\n"
        "Game runtime: NOT IMPLEMENTED.\\n"
        "No GT PSP executable is being run by this app.";
    return env->NewStringUTF(msg.c_str());
}
