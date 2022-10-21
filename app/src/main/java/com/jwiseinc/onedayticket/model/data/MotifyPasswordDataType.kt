package com.jwiseinc.onedayticket.model.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable



class MotifyPasswordDataType : Serializable {
    @SerializedName("sysCode")
    var sysCode: Int? = null
    @SerializedName("sysMsg")
    var sysMsg: String? = null
    @SerializedName("data")
    var data: MotifyDataContent? = null

    class MotifyDataContent : Serializable {
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
        @SerializedName("member_name")
        val member_name: NameDataType? = null
    }

    class NameDataType : Serializable {
        @SerializedName("en_US")
        val en_US: String? = null
        @SerializedName("zh_TW")
        val zh_TW: String? = null
    }

}