package com.jwiseinc.onedayticket.model.repository

import com.jwiseinc.onedayticket.ApiManager
import com.jwiseinc.onedayticket.BaseApplication.Companion.applicationContext
import com.jwiseinc.onedayticket.OnCallBack
import com.jwiseinc.onedayticket.utils.MD5Util
import com.sray.pigeonmap.utils.SharedPreferencesUtil
import okhttp3.*
import java.io.IOException

class MotifyPasswordRepository {

    fun resetPassword(authcode:String,newPassword:String,randNumber:String,task: OnCallBack) {
        val client = OkHttpClient()
        val builder = FormBody.Builder()
        builder.add("authcode",  authcode)
        builder.add("rand_num",  randNumber)
        builder.add("new_password",  MD5Util.md5(SharedPreferencesUtil.getKeyValue("memberID",applicationContext()) + newPassword))

        val request = Request.Builder()
            .method("POST", builder.build())
            .url(ApiManager.SERVER_USERS + "/merchant/reset_passwd.php")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                task.onError(e.message.toString())
            }
            override fun onResponse(call: Call, response: Response) {
                val resStr = response.body!!.string()
                task.onFinish(resStr)
            }
        })
    }
}
