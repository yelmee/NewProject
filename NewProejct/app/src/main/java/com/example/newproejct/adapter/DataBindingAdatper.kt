package com.example.newproejct.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .into(view)
    }
}

@BindingAdapter("text")
fun bindText(view: TextView, textString: String?) {
    if (!textString.isNullOrEmpty()) {
        view.text = textString
    }
}
    @BindingAdapter("intToText")
    fun bindText(view: TextView, textString: Int?) {
        if (textString != null) {
            view.text = textString.toString()
        }
    }