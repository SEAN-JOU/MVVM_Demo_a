package com.jwiseinc.onedayticket

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit



class ApiManager {
  companion object {
    val SERVER_USERS: String = "https://api-t.oride.jwiseinc.com"
    val client: OkHttpClient by lazy {
      OkHttpClient
        .Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()
    }
  }
}