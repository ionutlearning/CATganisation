package com.example.catganisation.presentation.ui.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.example.catganisation.R

fun ImageView.loadImage(url: String?) {
    url?.let {
        val currentDrawable = drawable
        val options = RequestOptions()
            .placeholder(getProgressDrawable(context))
            .error(currentDrawable)
        Glide.with(context)
            .setDefaultRequestOptions(options)
            .load(getUrl(it))
            .into(this)
    }
}

private fun getUrl(url: String?): GlideUrl {
    return GlideUrl(
        url,
        LazyHeaders.Builder()
            .addHeader("User-Agent", "https")
            .build()
    )
}

private fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 5f
        centerRadius = 25f
        setColorSchemeColors(context.resources.getColor(R.color.purple_200))
        start()
    }
}