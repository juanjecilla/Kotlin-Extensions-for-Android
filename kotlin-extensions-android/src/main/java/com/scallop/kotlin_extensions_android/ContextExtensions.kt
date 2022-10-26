package com.scallop.kotlin_extensions_android

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

// Show alert dialog
fun Context.showAlertDialog(
    positiveButtonLabel: String = "getString(R.string.okay)",
    title: String = "getString(R.string.app_name)", message: String,
    positiveButtonAction: () -> Unit
) {
    val builder = AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setCancelable(false)
        .setPositiveButton(positiveButtonLabel) { dialog, _ ->
            dialog.cancel()
            positiveButtonAction()
        }
    val alert = builder.create()
    alert?.show()
}

// Toast extensions
fun Context.showShortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context?.isOnline(): Boolean {
    this?.apply {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }
    return false
}

fun Context?.isOnline(
    failBlock: () -> Unit = { globalIntenetFailBock() },
    successBlock: () -> Unit
) {
    this?.apply {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        if (netInfo != null && netInfo.isConnected) {
            successBlock()
        } else {
            failBlock()
        }
    } ?: failBlock()
}

fun Context?.globalIntenetFailBock() {
    // show alter to user or implement custom code here
}

// https://cpaleop.medium.com/android-broadcastreceiver-as-flow-c8f5d80a7392
fun Context.networkBroadcastReceiverFlow(): Flow<Boolean> {
    return callbackFlow {

        val networkBroadcastReceiver = object : BroadcastReceiver() {

            override fun onReceive(context: Context?, intent: Intent) {
                if (intent.action != ConnectivityManager.CONNECTIVITY_ACTION) return
                val activeNetwork =
                    intent.extras?.get(ConnectivityManager.EXTRA_NETWORK_INFO) as NetworkInfo?
                        ?: return
                trySend(activeNetwork.isConnected)
            }
        }
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkBroadcastReceiver, filter)
        awaitClose { this@networkBroadcastReceiverFlow.unregisterReceiver(networkBroadcastReceiver) }
    }
}