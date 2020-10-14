package com.example.imagegallery.database

import androidx.room.*
@Dao
interface PhotoCommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotoComment(photoComments: PhotoComments)

    @Update
    fun update(photoComments: PhotoComments)

    @Delete
    fun delete(photoComments: PhotoComments)

    @Query("SELECT * FROM PhotoComments WHERE photoId == :photoId")
    fun getCommentsBy(photoId: String): List<PhotoComments>

    @Query("SELECT * FROM PhotoComments")
    fun getAllComments(): List<PhotoComments>
}