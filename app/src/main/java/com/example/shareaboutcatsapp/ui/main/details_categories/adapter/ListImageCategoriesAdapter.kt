package com.example.shareaboutcatsapp.ui.main.details_categories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.image.ImageModelItem
import kotlinx.android.synthetic.main.item_image.view.*

class ListImageCategoriesAdapter(private val listImage: List<ImageModelItem>) :
    RecyclerView.Adapter<ListImageCategoriesAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun dataBindViewHolder(imageModelItem: ImageModelItem) {
            Glide.with(itemView.context).load(imageModelItem.url).into(itemView.itemImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBindViewHolder(listImage[position])
    }

    override fun getItemCount(): Int = listImage.size
}