package com.jwiseinc.onedayticket

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Base64.DEFAULT
import android.util.Base64.encode
import androidx.annotation.RequiresApi
import com.google.android.gms.common.util.Base64Utils.encode
import java.net.URLEncoder.encode
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern


class ExampleUtil {
    companion object{
        fun ByteArray.toHexString() : String {
            return this.joinToString("") {
                java.lang.String.format(" "+"%02x", it)
            }
        }
        @RequiresApi(Build.VERSION_CODES.O)
        fun encodeBase64Hex(string:String?): String {
            val decodedBytes = Base64.getDecoder().decode(string)
            return  decodedBytes.toHexString()
        }

        fun getLocalVersionName(ctx: Context): String? {
            var localVersion = ""
            try {
                val packageInfo = ctx.applicationContext
                    .packageManager
                    .getPackageInfo(ctx.packageName, 0)
                localVersion = packageInfo.versionName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            return localVersion
        }

        fun getDateTime(d: Double): String? {
            try {
                val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm:ss")
                val netDate = Date(d.toLong())
                return sdf.format(netDate)
            } catch (e: Exception) {
                return e.toString()
            }
        }

        fun compareVersion(v1: String, v2: String): Boolean? {
            if (v1 == null || v1.length < 1 || v2 == null || v2.length < 1) return null
            val regEx = "[^0-9]"
            val p = Pattern.compile(regEx)
            var s1: String = p.matcher(v1).replaceAll("").trim()
            var s2: String = p.matcher(v2).replaceAll("").trim()

            val cha: Int = s1.length - s2.length
            var buffer = StringBuffer()
            var i = 0
            while (i < Math.abs(cha)) {
                buffer.append("0")
                ++i
            }

            if (cha > 0) {
                buffer.insert(0,s2)
                s2 = buffer.toString()
            } else if (cha < 0) {
                buffer.insert(0,s1)
                s1 = buffer.toString()
            }

            val s1Int = s1.toInt()
            val s2Int = s2.toInt()

            if (s1Int >= s2Int) return false
            else return true
        }
    }
}