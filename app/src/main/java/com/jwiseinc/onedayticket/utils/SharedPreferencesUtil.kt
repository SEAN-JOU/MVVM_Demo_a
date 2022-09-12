package com.sray.pigeonmap.utils

import android.content.Context

class SharedPreferencesUtil {
    companion object{
        fun setKeyValue(key:String,value:String,context:Context) {
            val sharedPreference = context.getSharedPreferences(key,Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putString(key,value)
            editor.commit()
        }
        fun getKeyValue(key:String,context:Context):String {
            val sharedPreference = context.getSharedPreferences(key,Context.MODE_PRIVATE)
            return sharedPreference.getString(key,"")!!
        }
    }
}