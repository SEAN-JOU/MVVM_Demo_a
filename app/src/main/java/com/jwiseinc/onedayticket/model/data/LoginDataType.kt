package com.sray.pigeonmap.model.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class LoginDataType : Serializable {
    @SerializedName("sysCode")
    var sysCode: Int? = null
    @SerializedName("sysMsg")
    var sysMsg: String? = null
    @SerializedName("data")
    var data: LoginDataContent? = null

    class LoginDataContent : Serializable{
        @SerializedName("session")
        val session: String? = null
        @SerializedName("expired_time")
        val expired_time: String? = null
    }

}