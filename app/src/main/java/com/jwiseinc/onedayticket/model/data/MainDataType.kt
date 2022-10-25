package com.jwiseinc.onedayticket.model.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MainDataType : Serializable {
    @SerializedName("sysCode")
    var sysCode: Int? = null
    @SerializedName("sysMsg")
    var sysMsg: String? = null
    @SerializedName("data")
    var data: MainDataContent? = null

    class MainDataContent : Serializable {
        @SerializedName("session_id")
        val session_id: String? = null
        @SerializedName("expired_time")
        val expired_time: String? = null
        @SerializedName("account")
        val account: String? = null
        @SerializedName("member_guid")
        val member_guid: String? = null
        @SerializedName("member_type")
        val member_type: Int? = null
        @SerializedName("account_name")
        val account_name: String? = null
        @SerializedName("member_email")
        val member_email: String? = null
        @SerializedName("account_email")
        val account_email: String? = null
        @SerializedName("renew_password")
        val renew_password: Boolean? = null
        @SerializedName("logo_url")
        val logo_url: String? = null
        @SerializedName("member_name")
        var  member_name: NameDataType? = null
    }

    class NameDataType : Serializable {
        @SerializedName("en_US")
        val en_US: String? = null
        @SerializedName("zh_TW")
        val zh_TW: String? = null
    }
}