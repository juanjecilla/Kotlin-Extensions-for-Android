package com.scallop.kotlin_extensions_android

import android.widget.Toast
import androidx.core.content.PermissionChecker
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

//fun Fragment.isGranted(permission: AppPermission) = run {
//    context?.let {
//        (PermissionChecker.checkSelfPermission(it, permission.permissionName
//        ) == PermissionChecker.PERMISSION_GRANTED)
//    } ?: false
//}
//
//fun Fragment.shouldShowRationale(permission: AppPermission) = run {
//    shouldShowRequestPermissionRationale(permission.permissionName)
//}
//
//fun Fragment.requestPermission(permission: AppPermission) {
//    requestPermissions(arrayOf(permission.permissionName), permission.requestCode
//    )
//}