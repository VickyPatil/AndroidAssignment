package com.example.imagegallery.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = PhotoDetails::class,
        parentColumns = ["id"], childColumns = ["photoId"])])
data class PhotoComments (
    var photoId:String,
    var comment:String? = "",
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
)