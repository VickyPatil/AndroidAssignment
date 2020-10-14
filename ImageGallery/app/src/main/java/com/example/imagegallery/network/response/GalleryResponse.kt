package com.example.imagegallery.network.response

import com.example.imagegallery.model.GalleryItem
import com.google.gson.annotations.SerializedName

class GalleryResponse {
    @SerializedName("data")
    val galleryItemList: ArrayList<GalleryItem>? = null

    @SerializedName("success")
    var isSuccess: Boolean? = null

    @SerializedName("status")
    var status:Int = 0
}