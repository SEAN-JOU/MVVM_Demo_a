package com.sray.pigeonmap.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class PigeonData : Serializable{
    @SerializedName("name")
    val name: String? = null
    @SerializedName("imageURL")
    val imageURL: String? = null
    @SerializedName("pid")
    val pid: String? = null
    @SerializedName("ownerID")
    val ownerID: String? = null
    @SerializedName("finalLongitude")
    val finalLongitude: String? = null
    @SerializedName("finalLatitude")
    val finalLatitude: String? = null
    @SerializedName("status")
    val status: String? = null
    @SerializedName("opentime")
    val opentime: String? = null
    @SerializedName("dataArr")
    val dataArr: ArrayList<DataArr>? = null
}

class DataArr : Serializable{
    @SerializedName("distance")
    val distance: Double? = 0.0
    @SerializedName("speed")
    val speed: Double? = 0.0
    @SerializedName("time")
    val time: String? = ""
    @SerializedName("longitude")
    val longitude: Double? = 0.0
    @SerializedName("latitude")
    val latitude: Double? = 0.0
    @SerializedName("height")
    val height: Double? = 0.0
}