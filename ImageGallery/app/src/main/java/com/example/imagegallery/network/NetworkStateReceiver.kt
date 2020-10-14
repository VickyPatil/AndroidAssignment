package com.example.imagegallery.network

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkRequest


class NetworkStateReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
    }


    fun registerNetworkCallback(context: Context?) {
        val cm:ConnectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder: NetworkRequest.Builder = NetworkRequest.Builder()

        cm.registerNetworkCallback(
            builder.build(),
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {

                }

                override fun onLost(network: Network) {

                }
            }
        )
    }

    interface NetworkStateReceiverListener {
        fun networkConnectivityChanged(isConnected: Boolean)
    }

    companion object {
        var networkStateReceiverListener: NetworkStateReceiverListener? = null
    }
}
