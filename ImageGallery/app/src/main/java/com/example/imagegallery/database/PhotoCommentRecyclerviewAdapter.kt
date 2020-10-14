package com.example.imagegallery.database

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagegallery.R
import com.example.imagegallery.model.GalleryItem
import com.example.imagegallery.network.apiinterface.inflate
import com.example.imagegallery.view.ImageDetailsActivity
import kotlinx.android.synthetic.main.recyclerview_comment_layout.view.*
import kotlinx.android.synthetic.main.recyclerview_gallery_item.view.*

class PhotoCommentRecyclerviewAdapter(private var comments: List<PhotoComments>) :
    RecyclerView.Adapter<PhotoCommentRecyclerviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_comment_layout, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = comments.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comments = comments[position]
        holder.bind(comments)
    }

    fun addComments(listOfItems: List<PhotoComments>) {
        this.comments = listOfItems
        notifyDataSetChanged()
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private var photoComment: PhotoComments? = null

        fun bind(photoComment: PhotoComments) {
            this.photoComment = photoComment
            view.tv_comment.text = photoComment.comment
        }
    }
}