package com.example.shareaboutcatsapp.ui.main.details_categories.adapter

import android.content.Context
import android.graphics.Point
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.image.ImageModelItem
import kotlinx.android.synthetic.main.item_image.view.*

class ListImageCategoriesAdapter(
    private val listImage: List<ImageModelItem>, private val onClick: (Int, String) -> Unit
) :
    RecyclerView.Adapter<ListImageCategoriesAdapter.ViewHolder>() {
    var screenWidth = 0
    var height = 0

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun dataBindViewHolder(imageModelItem: ImageModelItem) {
            val position = 1
            height = if (position == 1 || position == listImage.size - 1) {
                150
            } else {
                300
            }
            Glide.with(itemView.context).load(imageModelItem.url)
                .placeholder(R.drawable.img_placeholder).into(itemView.itemImage)
//            Glide.with(itemView.context).load(imageModelItem.url)
//                .placeholder(R.drawable.img_placeholder)
//                .apply(RequestOptions().override(screenWidth / 2, height)).into(itemView.itemImage)

            itemView.itemImage.setOnClickListener {
                onClick(adapterPosition, imageModelItem.id)
            }
        }
    }

    fun StaggeredGridLayoutAdapter(fragment: Fragment) {
        val wm = fragment.activity?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenWidth = size.x
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