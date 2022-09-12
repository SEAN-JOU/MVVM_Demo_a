package com.sray.pigeonmap.utils

import android.os.Handler
import android.os.Looper

class KTimer (val interval: Long , val count : Int? = null, val call:(() -> Unit)? = null) {
    private var mCountTag = 0
    private val runnable = object : Runnable{
        override fun run() {
            call?.invoke()
            if(count == null){
                MAIN_HANDLER.removeCallbacks(this)
                MAIN_HANDLER.postDelayed(this,interval)
            }else{
                if(mCountTag > count-2){
                    MAIN_HANDLER.removeCallbacks(this)
                }else{
                    mCountTag++
                    MAIN_HANDLER.postDelayed(this,interval
                    )
                }
            }
        }
    }
    fun start(){
        stop()
        MAIN_HANDLER.postDelayed(runnable,interval)
    }
    fun stop(){
        MAIN_HANDLER.removeCallbacks(runnable)
    }

    companion object{
        val MAIN_HANDLER = Handler(Looper.getMainLooper())
    }
}