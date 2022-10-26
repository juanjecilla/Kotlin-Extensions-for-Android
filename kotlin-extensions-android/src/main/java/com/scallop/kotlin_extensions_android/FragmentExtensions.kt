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

// Fragment related
inline fun <reified T : Any> Fragment.getValue(label: String, defaultValue: T? = null) = lazy {
    val value = arguments?.get(label)
    if (value is T) value else defaultValue
}

inline fun <reified T : Any> Fragment.getValueNonNull(label: String, defaultValue: T? = null) =
    lazy {
        val value = arguments?.get(label)
        requireNotNull(if (value is T) value else defaultValue) { label }
    }