package com.sray.pigeonmap.model.data

import com.google.gson.annotations.SerializedName
import com.sray.pigeonmap.model.DataArr
import java.io.Serializable

class VersionData : Serializable {
    @SerializedName("androidVersion")
    val androidVersion: String? = null
    @SerializedName("isAndroidForceUpdate")
    val isAndroidForceUpdate: Boolean? = null
    @SerializedName("androidURL")
    val androidURL: String? = null

    @SerializedName("iosVersion")
    val iosVersion: String? = null
    @SerializedName("isIosForceUpdate")
    val isIosForceUpdate: Boolean? = null
    @SerializedName("iosURL")
    val iosURL: String? = null
}
