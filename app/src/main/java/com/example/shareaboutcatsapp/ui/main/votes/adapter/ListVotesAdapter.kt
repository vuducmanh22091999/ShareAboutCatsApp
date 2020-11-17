package com.example.shareaboutcatsapp.ui.main.votes.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.votes.VotesModelItem
import kotlinx.android.synthetic.main.item_votes.view.*

class ListVotesAdapter(
    private val listVotesModelItem: List<VotesModelItem>,
    val onClick: (Int) -> Unit,
    val onClickDelete: (Int, ID: Int) -> Unit
) : RecyclerView.Adapter<ListVotesAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindDataViewHolder(votesModelItem: VotesModelItem) {
            itemView.itemMyVotesID.text = votesModelItem.id.toString()
            itemView.itemMyVotesCreatedAt.text = votesModelItem.created_at
            itemView.itemImageDeleteMyVotes.setImageResource(R.drawable.ic_delete)

            itemView.setOnClickListener {
                onClick(adapterPosition)
            }

            itemView.itemImageDeleteMyVotes.setOnClickListener {
                onClickDelete(adapterPosition, votesModelItem.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_votes, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDataViewHolder(listVotesModelItem[position])
    }

    override fun getItemCount(): Int = listVotesModelItem.size
}