package com.jwiseinc.onedayticket.model.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class WriteOffRecordDataType : Serializable {
    @SerializedName("sysCode")
    var sysCode: Int? = null
    @SerializedName("sysMsg")
    var sysMsg: String? = null
    @SerializedName("data")
    var data: ArrayList<WriteOffRecordDataContent>? = null

    class WriteOffRecordDataContent : Serializable {
        @SerializedName("account")
        val account: String? = null
        @SerializedName("session_id")
        val session_id: String? = null
        @SerializedName("member_guid")
        val member_guid: String? = null
        @SerializedName("member_type")
        val member_type: Int? = null
        @SerializedName("account_name")
        val account_name: String? = null
        @SerializedName("expired_time")
        val expired_time: String? = null
    }
}