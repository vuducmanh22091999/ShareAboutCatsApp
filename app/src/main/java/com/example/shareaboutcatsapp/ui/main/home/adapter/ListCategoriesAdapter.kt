package com.example.shareaboutcatsapp.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.categories.CategoriesModelItem
import kotlinx.android.synthetic.main.item_categories.view.*

class ListCategoriesAdapter(private val listCategoriesModelItem: List<CategoriesModelItem>) :
    RecyclerView.Adapter<ListCategoriesAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindDataViewHolder(categoriesModelItem: CategoriesModelItem) {
            itemView.itemCategories.text = categoriesModelItem.name
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