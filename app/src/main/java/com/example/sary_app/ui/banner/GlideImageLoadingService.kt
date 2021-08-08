package com.example.sary_app.ui.banner

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.BaseRequestOptions
import com.example.sary_app.R
import ss.com.bannerslider.ImageLoadingService

class GlideImageLoadingService(val context: Context?): ImageLoadingService {



    override fun loadImage(url: String?, imageView: ImageView?) {
        Glide.with(context!!).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.default_err)
                .into(imageView!!)
    }

    override fun loadImage(resource: Int, imageView: ImageView?) {
        Glide.with(context!!).load(resource)
                .into(imageView!!)
    }

    override fun loadImage(url: String?, placeHolder: Int, errorDrawable: Int, imageView: ImageView?) {
        Glide.with(context!!).load(url).placeholder(placeHolder)
                .error(errorDrawable).into(imageView!!)
    }
}