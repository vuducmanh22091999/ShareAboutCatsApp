package com.example.shareaboutcatsapp.ui.main.breeds

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.breeds.BreedsModelItem
import com.example.shareaboutcatsapp.data.model.breeds.ImageBreedsModelItemTest
import kotlinx.android.synthetic.main.item_image.view.*
import kotlinx.android.synthetic.main.item_image_breeds.view.*

class ListImageBreedsAdapter(private val listImageBreedsModelItem: List<ImageBreedsModelItemTest>) :
    RecyclerView.Adapter<ListImageBreedsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindDataViewHolder(imageBreedsModelItem: ImageBreedsModelItemTest) {
            Glide.with(itemView.context).load(imageBreedsModelItem.url)
                .placeholder(R.drawable.img_placeholder).override(200, 200).into(itemView.itemImageBreeds)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_image_breeds, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDataViewHolder(listImageBreedsModelItem[position])
    }

    override fun getItemCount(): Int = listImageBreedsModelItem.size
}