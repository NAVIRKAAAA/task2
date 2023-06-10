package com.example.recyclerview.utils.ext


import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.recyclerview.R

fun ImageView.loadImage(image: String) {
    Glide.with(this)
        .load(image)
        .circleCrop()
        .placeholder(R.drawable.ic_user_photo)
        .error(R.drawable.ic_user_photo)
        .into(this)
}
