package com.scallop.kotlin_extensions_android

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast


fun Activity.hideKeyboard() {
    val inputManager: InputMethodManager =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(
        currentFocus?.windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
    )
}

fun Activity.showToast(id: Int) {
    Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
}

fun Activity.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

// Activity related
inline fun <reified T : Any> Activity.getValue(
    label: String, defaultValue: T? = null
) = lazy {
    val value = intent?.extras?.get(label)
    if (value is T) value else defaultValue
}

inline fun <reified T : Any> Activity.getValueNonNull(
    label: String, defaultValue: T? = null
) = lazy {
    val value = intent?.extras?.get(label)
    requireNotNull((if (value is T) value else defaultValue)) { label }
}