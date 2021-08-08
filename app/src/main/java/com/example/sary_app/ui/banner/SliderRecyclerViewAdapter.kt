package com.example.sary_app.ui.banner

import android.app.Activity
import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder

class SliderRecyclerViewAdapter(val imageList: List<String> , activity: Activity) : SliderAdapter() {


    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindImageSlide(position: Int, imageSlideViewHolder: ImageSlideViewHolder) {
           imageSlideViewHolder.bindImageSlide(imageList[position])
    }

}