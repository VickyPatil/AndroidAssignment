package com.example.imagegallery.network.apiinterface

import com.example.imagegallery.model.GalleryItem

interface IGalleryItems {
    fun onSuccess(galleryItems:List<GalleryItem>?)
    fun onFailure()
}