package com.eblee.pokeguide.presentation.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

object BindingAdapterUtils {
    //,"app:placeholder"
    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String?) {
        url?.let {
            Glide.with(imageView.context)
                .load(url)
//            .placeholder(placeholder)
//            .error(placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .apply(RequestOptions().fitCenter())
                .into(imageView)
        }
    }
}