package com.jwiseinc.onedayticket.model.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ForgetPasswordDataType {
    @SerializedName("sysCode")
    var sysCode: Int? = null
    @SerializedName("sysMsg")
    var sysMsg: String? = null
    @SerializedName("data")
    var data: ForgetPasswordDataContent? = null

    class ForgetPasswordDataContent : Serializable {
        @SerializedName("session")
        val session: String? = ""
        @SerializedName("expired_time")
        val expired_time: String? = ""
    }
}