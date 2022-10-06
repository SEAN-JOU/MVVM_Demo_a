package com.sray.pigeonmap.model.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class StandardData : Serializable {
    @SerializedName("sysCode")
    var sysCode: String? = null
    @SerializedName("sysMsg")
    var sysMsg: String? = null
    @SerializedName("data")
    var data: String? = null
}