package com.example.bindingadapters

import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

class Utils {

    // Layout Load time run ones
    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl", "error")
        fun loadImage(view: ImageView, url: String, error: Drawable) {
            Picasso.get().load(url).error(error).into(view)
        }

        @JvmStatic
        @BindingAdapter("loadProfileUrl", "error")
        fun loadProfileUrl(view: ImageView, url: String, error: Drawable) {
            Picasso.get().load(url).error(error).into(view)
        }
    }
}