package com.example.imagegallery.network.apiinterface

import android.content.Context
import android.util.Log
import com.example.imagegallery.BuildConfig
import com.example.imagegallery.network.response.GalleryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConnectionManger() {

    private val TAG = "ConnectionManger."
    private var apiInterface = RetrofitClient.instance?.create(APIInterface::class.java)!!

    fun getGalleryItems(searchField:String,galleryCallback:IGalleryItems){
        val callGalleryItem = apiInterface.getGalleryItems(searchField)
        callGalleryItem.enqueue(object : Callback<GalleryResponse> {
            override fun onResponse(call: Call<GalleryResponse>, response: Response<GalleryResponse>) {
                val galleryResponse = response.body()
                if(galleryResponse != null && galleryResponse.status == 200){
                    galleryCallback.onSuccess(galleryResponse?.galleryItemList)
                }else{
                    galleryCallback.onFailure()
                }
            }

            override fun onFailure(call: Call<GalleryResponse>, t: Throwable) {
                galleryCallback.onFailure()
            }

        })
    }
}