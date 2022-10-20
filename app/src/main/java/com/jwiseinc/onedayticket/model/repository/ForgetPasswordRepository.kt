package com.jwiseinc.onedayticket.model.repository

import com.jwiseinc.onedayticket.ApiManager
import com.jwiseinc.onedayticket.OnCallBack
import okhttp3.*
import java.io.IOException

class ForgetPasswordRepository {

    fun forget(memberID:String,email:String,task: OnCallBack) {
        val client = OkHttpClient()
        val builder = FormBody.Builder()
        builder.add("memberID",  memberID)
        builder.add("email",  email)

        val request = Request.Builder()
            .method("POST", builder.build())
            .url(ApiManager.SERVER_USERS + "/merchant/forget.php")
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