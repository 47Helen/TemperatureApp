#include <jni.h>

// This method is used to convert temperature from Celsius to Fahrenheit, or vice versa.
JNIEXPORT jfloatArray JNICALL
Java_com_example_helen47_temperatureapp_TempConvertActivity_convertTemperature(JNIEnv *env,
                                                                               jobject instance,
                                                                               jfloatArray floatArray_,
                                                                               jboolean bool) {
    // Get the size of the float array
    jsize length = (*env)->GetArrayLength(env, floatArray_);
    // Create a new float array
    jfloatArray newArray = (*env)->NewFloatArray(env, length);

    // Create two pointers for these two arrays
    jfloat *oarr = (*env)->GetFloatArrayElements(env, floatArray_, 0);
    jfloat *narr = (*env)->GetFloatArrayElements(env, newArray, 0);

    // Convert the temperature data
    int i;
    for(i = 0; i < length; i++) {
        if(bool) {
            // Celsius to Fahrenheit
            narr[i] = (oarr[i] * 9.0f) / 5.0f + 32.0f;
        } else {
            // Fahrenheit to Celsius
            narr[i] = ((oarr[i] - 32.0f) * 5.0f) / 9.0f;
        }
    }

    // Release two pointers
    (*env)->ReleaseFloatArrayElements(env, newArray, narr, 0);
    (*env)->ReleaseFloatArrayElements(env, floatArray_, oarr, 0);

    // Return new temperature data
    return newArray;

}