package com.example.imagegallery.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.imagegallery.R

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var mTextViewScreenTitle: TextView
    private lateinit var mImageButtonBack: ImageButton
    private lateinit var mProgressBar: ProgressBar
    private lateinit var constraintLayout:ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerNetworkCallback()
    }

    override fun setContentView(layoutResID: Int) {
        constraintLayout = layoutInflater.inflate(
            R.layout.activity_base,
            null
        ) as ConstraintLayout

        var activityContainer: FrameLayout  = constraintLayout.findViewById(R.id.layout_container)
        mTextViewScreenTitle = constraintLayout.findViewById(R.id.text_screen_title) as TextView
        mImageButtonBack = constraintLayout.findViewById(R.id.image_back_button)
        mProgressBar = constraintLayout.findViewById(R.id.progress_circular)

        val toolbar = constraintLayout.findViewById(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)

        layoutInflater.inflate(layoutResID, activityContainer, true)
        super.setContentView(constraintLayout)
    }


    fun setScreenTitle(resId: Int) {
        mTextViewScreenTitle.text = getString(resId)
    }

    fun setScreenTitle(title: String) {
        mTextViewScreenTitle.text = title
    }

    fun getBackButton(): ImageButton {
        return mImageButtonBack;
    }

    fun showProgressDialog() {
        if(mProgressBar.visibility == View.GONE) {
            mProgressBar.visibility = View.VISIBLE
        }
    }

    fun dismissProgressDialog() {
        if(mProgressBar.visibility == View.VISIBLE) {
            mProgressBar.visibility = View.GONE
        }
    }

    fun setBackButtonVisibility(visibility:Int){
        mImageButtonBack.visibility = visibility
    }

    private fun registerNetworkCallback() {
        val cm:ConnectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val builder: NetworkRequest.Builder = NetworkRequest.Builder()
            cm.registerNetworkCallback(

                builder.build(),
                object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        onNetworkConnected()
                    }

                    override fun onLost(network: Network) {
                        onNetworkLost()
                    }
                }
            )
        }
    }

    abstract fun onNetworkConnected()
    abstract fun onNetworkLost()
}