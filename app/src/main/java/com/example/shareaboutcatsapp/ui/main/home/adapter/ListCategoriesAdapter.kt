package com.example.shareaboutcatsapp.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.categories.CategoriesModelItem
import com.example.shareaboutcatsapp.data.model.image.ImageModelItem
import kotlinx.android.synthetic.main.item_categories.view.*

class ListCategoriesAdapter(
    private val listCategoriesModelItem: List<CategoriesModelItem>,
    val onClick: (Int) -> Unit
) :
    RecyclerView.Adapter<ListCategoriesAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindDataViewHolder(categoriesModelItem: CategoriesModelItem) {
            itemView.itemCategories.text = categoriesModelItem.name
            Glide.with(itemView.context).load(R.drawable.gif_splash)
                .placeholder(R.drawable.img_placeholder).override(100, 100)
                .into(itemView.itemImageCategories)

            itemView.setOnClickListener {
                onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_categories, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDataViewHolder(listCategoriesModelItem[position])
    }

    override fun getItemCount(): Int = listCategoriesModelItem.size
}