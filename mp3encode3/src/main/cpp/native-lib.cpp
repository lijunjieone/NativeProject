#include <jni.h>
#include <string>

int openFile(char *pngFilePath);
typedef unsigned char byte;
FILE* pngFile;
byte* compressedData;
int actualSize;
char* p;
extern "C" JNIEXPORT jstring JNICALL
Java_com_a_news_mp3encode_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {

    std::string hello = "Hello from C++";

//    std::string str = "/sdcard/Pictures/1.png";
//
//    p = const_cast<char *>(str.c_str());
//    int size = openFile(p);
    return env->NewStringUTF(hello.c_str());
}


int openFile(char *pngFilePath) {
    pngFile = fopen(pngFilePath, "rb");

    fseek(pngFile, 0, SEEK_END);
    int data_length = ftell(pngFile);
    rewind(pngFile);
    compressedData = new byte[data_length];
    actualSize = fread(compressedData, 1,data_length, pngFile);
    return actualSize;
}
