package com.scallop.kotlin_extensions_android

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast

//Extensions
//fun Int.asColor() = ContextCompat.getColor(ApplicationCalss.instance, this)
//fun Int.asDrawable() = ContextCompat.getDrawable(MavrikApplication.instance, this)

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
        .setPositiveButton(positiveButtonLabel) { dialog, id ->
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

fun Context?.isOnline(failBlock : () -> Unit  = { globalIntenetFailBock() }, successBlock : () -> Unit ) {
    this?.apply {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        if (netInfo != null && netInfo.isConnected){
            successBlock()
        }else{
            failBlock()
        }
    }?:failBlock()
}

fun Context?.globalIntenetFailBock(){
    // show alter to user or implement custom code here
}