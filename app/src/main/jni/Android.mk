LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := temperature-app
LOCAL_SRC_FILES := temperature-app.c

include $(BUILD_SHARED_LIBRARY)