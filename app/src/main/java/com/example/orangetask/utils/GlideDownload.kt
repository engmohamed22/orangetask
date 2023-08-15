package com.example.orangetask.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.orangetask.R

    fun ImageView.loadImage(url: String) {
        Glide.with(this)
            .load(url)
            .error(R.drawable.ic_image)
            .into(this)
    }

