package com.example.imagegallery.database

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagegallery.R
import com.example.imagegallery.model.GalleryItem
import com.example.imagegallery.network.apiinterface.inflate
import com.example.imagegallery.view.ImageDetailsActivity
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import kotlinx.android.synthetic.main.recyclerview_gallery_item.view.*

class GalleryRecyclerViewAdapter(private var galleryItems: ArrayList<GalleryItem>) : RecyclerView.Adapter<GalleryRecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_gallery_item, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = galleryItems.size

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val galleryItem = galleryItems[position]
        holder.bind(galleryItem)
    }

    fun setGalleryItems(listOfItems: ArrayList<GalleryItem>) {
        this.galleryItems.addAll(listOfItems)
        notifyDataSetChanged()
    }
    //1
    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        //2
        private var galleryItem: GalleryItem? = null

        //3
        init {
            view.setOnClickListener(this)
        }

        fun bind(galleryItem: GalleryItem) {
            this.galleryItem = galleryItem
            val galleryImages = galleryItem.images
            if(galleryImages != null && galleryImages.isNotEmpty()){
                val url = galleryImages[0].link
                Glide.with(view.context)  //2
                    .load(url) //3
                    .centerCrop() //4
                    .into(view.iv_gallery) //8

                view.tv_title.text = galleryItem.title
            }

        }

        //4
        override fun onClick(v: View) {
            val context = itemView.context
            val showPhotoIntent = Intent(context, ImageDetailsActivity::class.java)
            showPhotoIntent.putExtra(PHOTO_KEY, galleryItem)
            context.startActivity(showPhotoIntent)
        }

        companion object {
            //5
            private val PHOTO_KEY = "PHOTO"
        }
    }
}