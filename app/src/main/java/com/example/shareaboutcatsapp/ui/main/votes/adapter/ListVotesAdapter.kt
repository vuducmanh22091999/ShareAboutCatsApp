package com.example.shareaboutcatsapp.ui.main.votes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.votes.VotesModelItem
import kotlinx.android.synthetic.main.item_votes.view.*

class ListVotesAdapter(private val listVotesModelItem: List<VotesModelItem>) :RecyclerView.Adapter<ListVotesAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindDataViewHolder(votesModelItem: VotesModelItem) {
            itemView.itemMyVotes.text = votesModelItem.created_at
            itemView.itemImageDeleteMyVotes.setImageResource(R.drawable.ic_delete)
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