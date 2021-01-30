package com.scallop.kotlin_extensions_android

import android.widget.Toast
import androidx.fragment.app.Fragment


fun Fragment.hideKeyboard() {
    activity?.hideKeyboard()
}

fun Fragment.showToast(id: Int) {
    Toast.makeText(context, id, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}
