package com.example.imagegallery.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class PhotoDetails (
    @PrimaryKey
    val id : String,
    val title : String? = "",
    val link : String?
)