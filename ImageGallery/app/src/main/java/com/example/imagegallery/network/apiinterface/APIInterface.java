package com.example.imagegallery.network.apiinterface;

import com.example.imagegallery.BuildConfig;
import com.example.imagegallery.network.response.GalleryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APIInterface{
    @GET(BuildConfig.Method+"search/1")
    Call<GalleryResponse> getGalleryItems(@Query("q") String searchText);
}
