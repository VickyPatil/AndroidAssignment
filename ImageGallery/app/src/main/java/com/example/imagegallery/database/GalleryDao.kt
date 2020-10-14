package com.example.imagegallery.database

import androidx.room.*

@Dao
interface GalleryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotoDetails(photoDetails: PhotoDetails)

    @Update
    fun update(photoDetails: PhotoDetails)

    @Delete
    fun delete(photoDetails: PhotoDetails)

    @Query("SELECT * FROM PhotoDetails WHERE title == :name")
    fun getPhotoByName(name: String): List<PhotoDetails>

    @Query("SELECT * FROM PhotoDetails")
    fun getPhotoDetails(): List<PhotoDetails>
}