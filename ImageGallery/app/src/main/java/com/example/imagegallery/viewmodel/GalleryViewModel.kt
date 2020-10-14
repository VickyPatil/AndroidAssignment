package com.example.imagegallery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imagegallery.model.GalleryItem
import com.example.imagegallery.network.apiinterface.ConnectionManger
import com.example.imagegallery.network.apiinterface.IGalleryItems

class GalleryViewModel:ViewModel() {
    val galleryList = MutableLiveData<List<GalleryItem>>()
    val isRequesting = MutableLiveData<Boolean>()

    fun getGalleryItems(searchText:String){
        isRequesting.value = true
        ConnectionManger().getGalleryItems(searchText,object :IGalleryItems{
            override fun onSuccess(galleryItems: List<GalleryItem>?) {
                isRequesting.value = false
                galleryList.value = galleryItems
            }

            override fun onFailure() {
                isRequesting.value = false;
            }

        })
    }
}