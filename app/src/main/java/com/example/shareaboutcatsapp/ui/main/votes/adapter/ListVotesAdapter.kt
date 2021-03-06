package com.example.shareaboutcatsapp.ui.main.votes.adapter

import android.annotation.SuppressLint
import android.provider.Settings.System.DATE_FORMAT
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.votes.VotesModelItem
import kotlinx.android.synthetic.main.item_votes.view.*


class ListVotesAdapter(
    private var listVotesModelItem: List<VotesModelItem>,
    val onClick: (Int, ID: Int) -> Unit,
    val onClickDelete: (Int, ID: Int) -> Unit
) : RecyclerView.Adapter<ListVotesAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindDataViewHolder(votesModelItem: VotesModelItem) {
            itemView.itemMyVotesID.text = votesModelItem.id.toString()
            val date = votesModelItem.created_at
            val time = votesModelItem.created_at
            val formatDate = date.substring(0,10)
            val formatTime = time.substring(11,19)
            itemView.itemMyVotesCreatedAt.text = "$formatDate $formatTime"
            itemView.itemImageDeleteMyVotes.setImageResource(R.drawable.ic_delete)

            itemView.setOnClickListener {
                onClick(adapterPosition, votesModelItem.id)
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

    fun filterVotes(filteredVotes: List<VotesModelItem>) {
        listVotesModelItem = filteredVotes
        notifyDataSetChanged()
    }
}