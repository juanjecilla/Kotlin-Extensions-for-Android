package com.scallop.kotlin_extensions_android

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

fun View.animatedVisible(visible: Boolean) {
    if (visible) {
        animate().alpha(1f).setDuration(300).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                visible(true)
            }
        })
    } else {
        animate().alpha(0f).setDuration(300).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                visible(false)
            }
        })
    }
}

fun View.visible(visible: Boolean) {
    if (visible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}
