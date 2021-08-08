package com.example.sary_app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sary_app.R
import com.example.sary_app.model.CatalogItem
import com.example.sary_app.utils.loadAsyncImage
import kotlin.properties.Delegates

class CatalogAdapter(val layout: Int) : RecyclerView.Adapter<CatalogAdapter.DataViewHolder>(), AutoUpdatableAdapter {

    var items: List<CatalogItem> by Delegates.observable(emptyList()) {
        prop, old, new ->
        autoNotify(old, new) { o, n -> o.imageUrl == n.imageUrl }
    }

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(item: CatalogItem?) {

            item?.let {data ->
                itemView.apply {
                    val icon = findViewById<ImageView>(R.id.smart_imageView)
                    icon?.loadAsyncImage(data.imageUrl)
                    data.title?.let {
                        val title = findViewById<TextView>(R.id.title)
                        title?.text = it
                    }

                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
            DataViewHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(items[position])
    }

}
