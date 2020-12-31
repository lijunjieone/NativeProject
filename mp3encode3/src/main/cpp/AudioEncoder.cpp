#include "Mp3Encoder.h"
#include "libmp3_encoder/mp3_encoder.h"
#include "common/CommonTools.h"

#define LOG_TAG "Mp3Encoder"

Mp3Encoder* encoder = NULL;
extern "C"
JNIEXPORT jint JNICALL Java_com_a_news_mp3encode_Mp3Encoder_init
  (JNIEnv * env, jobject obj, jstring pcmPathParam, jint channels, jint bitRate,
			jint sampleRate, jstring mp3PathParam) {
	const char* pcmPath = env->GetStringUTFChars(pcmPathParam, NULL);
	const char* mp3Path = env->GetStringUTFChars(mp3PathParam, NULL);
	LOGI("mp3Path is %s...", mp3Path);
	encoder = new Mp3Encoder();
	encoder->Init(pcmPath, mp3Path, sampleRate, channels, bitRate);
	env->ReleaseStringUTFChars(mp3PathParam, mp3Path);
	env->ReleaseStringUTFChars(pcmPathParam, pcmPath);
}
extern "C"
JNIEXPORT void JNICALL Java_com_a_news_mp3encode_Mp3Encoder_encode(JNIEnv * env, jobject obj) {
//	if(NULL != encoder) {
//		encoder->Encode();
//	}
}
extern "C"
JNIEXPORT void JNICALL Java_com_a_news_mp3encode_Mp3Encoder_destroy(JNIEnv * env, jobject obj) {
//	if(NULL != encoder) {
//		encoder->Destory();
//		delete encoder;
//		encoder = NULL;
//	}
}
