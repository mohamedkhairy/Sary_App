package com.example.sary_app.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sary_app.R


fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.hideView() {
    this.visibility = View.GONE
}

fun View.invisibleView() {
    this.visibility = View.INVISIBLE
}

fun ImageView.loadAsyncImage(url: String?) {
    Glide.with(this.context).run {
        if (url.isNullOrBlank())
         return@run load(R.drawable.default_err)
        else
            return@run load(url)
    }.apply(RequestOptions().override(width, height))
        .error(R.drawable.default_err)
        .transition(GenericTransitionOptions.with<Drawable>(android.R.anim.fade_in)).into(this)

}