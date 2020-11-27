package com.example.shareaboutcatsapp.ui.main.breeds

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.breeds.BreedsModelItem
import kotlinx.android.synthetic.main.item_image.view.*

class ListImageBreedsAdapter(private val listBreedsModelItem: List<BreedsModelItem>) :
    RecyclerView.Adapter<ListImageBreedsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindDataViewHolder(breedsModelItem: BreedsModelItem) {
//            Glide.with(itemView.context).load(breedsModelItem.url)
//                .placeholder(R.drawable.img_placeholder).into(itemView.itemImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDataViewHolder(listBreedsModelItem[position])
    }

    override fun getItemCount(): Int = listBreedsModelItem.size
}