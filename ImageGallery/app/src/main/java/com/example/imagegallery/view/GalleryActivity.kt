package com.example.imagegallery.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.recyclerview.widget.DefaultItemAnimator

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagegallery.R
import com.example.imagegallery.database.GalleryRecyclerViewAdapter
import com.example.imagegallery.model.GalleryItem
import com.example.imagegallery.network.apiinterface.ConnectionManger
import com.example.imagegallery.network.apiinterface.IGalleryItems
import com.example.imagegallery.viewmodel.GalleryViewModel

class GalleryActivity : BaseActivity() {
    private lateinit var mRecyclerView:RecyclerView
    private lateinit var mEditTextSearch:EditText
    private  var adapter: GalleryRecyclerViewAdapter? = null

    private val viewModel:GalleryViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        initViews()
        setUpObservers()
    }

    private fun initViews() {
        mRecyclerView = findViewById(R.id.recyclerview_gallery);
        setScreenTitle(getString(R.string.gallery_screen_title))
        setBackButtonVisibility(View.GONE)
        setUpRecyclerView()
    }

    private fun setUpObservers(){
        viewModel.getGalleryItems("vanilla")
        viewModel.isRequesting.observe(this, Observer {
            if(it){
                showProgressDialog()
            }else{
                dismissProgressDialog()
            }
        })
        viewModel.galleryList.observe(this, Observer {
            if(adapter == null){
                adapter = GalleryRecyclerViewAdapter(ArrayList(it))
                mRecyclerView.adapter = adapter
            }else{
                adapter?.setGalleryItems(ArrayList(it))
            }
        })
    }

    private fun setUpRecyclerView(){
        var gridLayoutManager = GridLayoutManager(this,3)
        mRecyclerView.layoutManager = gridLayoutManager
    }

    override fun onNetworkConnected() {

    }

    override fun onNetworkLost() {

    }
}