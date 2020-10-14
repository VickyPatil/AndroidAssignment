package com.example.imagegallery.model

import android.os.Parcel
import android.os.Parcelable
import com.example.imagegallery.network.response.Ad_config
import com.example.imagegallery.network.response.Images
import com.example.imagegallery.network.response.Tags
import java.io.Serializable

data class GalleryItem(
    val id : String,
    val title : String? = "",
    val description : String?,
    val datetime : Int,
    val cover : String?,
    val cover_width : Int,
    val cover_height : Int,
    val account_url : String?,
    val account_id : Int,
    val privacy : String?,
    val layout : String?,
    val views : Int,
    val link : String?,
    val ups : Int,
    val downs : Int,
    val points : Int,
    val score : Int,
    val is_album : Boolean,
    val vote : String?,
    val favorite : Boolean,
    val nsfw : Boolean,
    val section : String?,
    val comment_count : Int,
    val favorite_count : Int,
    val topic : String?,
    val topic_id : Int,
    val images_count : Int,
    val in_gallery : Boolean,
    val is_ad : Boolean,
    val tags : List<Tags>?,
    val ad_type : Int,
    val ad_url : String?,
    val in_most_viral : Boolean,
    val include_album_ads : Boolean,
    val images : List<Images>?,
    val ad_config : Ad_config
):Serializable