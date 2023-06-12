package com.example.recyclerview.utils.ext

import android.view.View
import android.view.ViewPropertyAnimator

fun View.animateVisibility(visibility: Int) {
    val animator: ViewPropertyAnimator = when (visibility) {
        View.VISIBLE -> animate().alpha(1f).setDuration(300)
        View.GONE -> animate().alpha(0f).setDuration(300)
        else -> return
    }
    animator.withStartAction {
        this.visibility = View.VISIBLE
    }.withEndAction {
        this.visibility = visibility
    }.start()
}