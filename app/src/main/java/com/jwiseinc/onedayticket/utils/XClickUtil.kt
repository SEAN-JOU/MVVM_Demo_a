package com.sray.pigeonmap.utils

import android.view.View

class XClickUtil {

    companion object {
        private var mLastClickTime: Long = 0
        private var mLastClickViewId = 0

        fun isFastDoubleClick(v: View): Boolean {
            val intervalMillis: Long = 500
            val viewId = v.id
            val time = System.currentTimeMillis()
            val timeInterval = Math.abs(time - mLastClickTime)
            return if (timeInterval < intervalMillis && viewId == mLastClickViewId) {
                true
            } else {
                mLastClickTime = time
                mLastClickViewId = viewId
                false
            }
        }

        fun isFastDoubleClick1(v: View): Boolean {
            val intervalMillis: Long = 1500
            val viewId = v.id
            val time = System.currentTimeMillis()
            val timeInterval = Math.abs(time - mLastClickTime)
            return if (timeInterval < intervalMillis && viewId == mLastClickViewId) {
                true
            } else {
                mLastClickTime = time
                mLastClickViewId = viewId
                false
            }
        }

        fun isFastDoubleClick2(v: View): Boolean {
            val viewId = v.id
            val intervalMillis: Long = 10000
            val time = System.currentTimeMillis()
            val timeInterval = Math.abs(time - mLastClickTime)
            return if (timeInterval < intervalMillis && viewId == mLastClickViewId) {
                true
            } else {
                mLastClickTime = time
                mLastClickViewId = viewId
                false
            }
        }

        fun isFastDoubleCustomTimeClick(v: View, intervalMillis: Long): Boolean {
            val viewId = v.id
            val time = System.currentTimeMillis()
            val timeInterval = Math.abs(time - mLastClickTime)
            return if (timeInterval < intervalMillis && viewId == mLastClickViewId) {
                true
            } else {
                mLastClickTime = time
                mLastClickViewId = viewId
                false
            }
        }
    }
}
