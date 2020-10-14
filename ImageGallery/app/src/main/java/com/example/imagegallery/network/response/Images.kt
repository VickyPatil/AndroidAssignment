package com.example.imagegallery.network.response

import java.io.Serializable

data class Images (
    val id : String?,
    val title : String?,
    val description : String?,
    val datetime : Int,
    val type : String?,
    val animated : Boolean,
    val width : Int,
    val height : Int,
    val size : Int,
    val views : Int,
    val bandwidth : String?,
    val vote : String?,
    val favorite : Boolean,
    val nsfw : String?,
    val section : String?,
    val account_url : String?,
    val account_id : String?,
    val is_ad : Boolean,
    val in_most_viral : Boolean,
    val has_sound : Boolean,
    val tags : List<String?>,
    val ad_type : Int,
    val ad_url : String?,
    val edited : Int,
    val in_gallery : Boolean,
    val link : String? = "",
    val comment_count : String?,
    val favorite_count : String?,
    val ups : String?,
    val downs : String?,
    val points : String?,
    val score : String?
): Serializable