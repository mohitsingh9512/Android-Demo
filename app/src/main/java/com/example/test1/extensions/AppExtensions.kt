package com.example.test1.extensions

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager

fun Context.isNetworkAvailable(): Boolean {
    val connManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connManager.getNetworkCapabilities(connManager.activeNetwork)
    return networkCapabilities != null
}

data class InfoLanguage(val languageCode: String)