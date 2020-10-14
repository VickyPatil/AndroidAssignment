package com.example.imagegallery.network.response

import java.io.Serializable

data class Ad_config (

    val safeFlags : List<String>?,
    val highRiskFlags : List<String>?,
    val unsafeFlags : List<String>?,
    val wallUnsafeFlags : List<String>?,
    val showsAds : Boolean
):Serializable