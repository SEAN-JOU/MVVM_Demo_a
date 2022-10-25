package com.jwiseinc.onedayticket.model.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class VersionDataType {
    @SerializedName("sysCode")
    var sysCode: Int? = null
    @SerializedName("sysMsg")
    var sysMsg: String? = null
    @SerializedName("data")
    var data: VersionDataContent? = null

    class VersionDataContent : Serializable {
        @SerializedName("ios_version")
        val ios_version: String? = null
        @SerializedName("ios_app_path")
        val ios_app_path: String? = null
        @SerializedName("ios_need_update")
        val ios_need_update: Boolean? = null
        @SerializedName("android_version")
        val android_version: String? = null
        @SerializedName("android_app_path")
        val android_app_path: String? = null
        @SerializedName("android_need_update")
        val android_need_update: Boolean? = null
    }
}