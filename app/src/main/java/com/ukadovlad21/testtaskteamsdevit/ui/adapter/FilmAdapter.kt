package com.ukadovlad21.testtaskteamsdevit.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ukadovlad21.testtaskteamsdevit.R
import com.ukadovlad21.testtaskteamsdevit.models.ItemDataModel
import com.ukadovlad21.testtaskteamsdevit.ui.activity.MainActivity
import com.ukadovlad21.testtaskteamsdevit.utils.Constant.Companion.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class FilmAdapter() : ListAdapter<ItemDataModel, FilmItemHolder>(ItemComparator()) {

    class ItemComparator : DiffUtil.ItemCallback<ItemDataModel>() {
        override fun areItemsTheSame(oldItem: ItemDataModel, newItem: ItemDataModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ItemDataModel, newItem: ItemDataModel): Boolean {
            return oldItem.name == newItem.name

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmItemHolder =
        FilmItemHolder(parent)

    override fun onBindViewHolder(holder: FilmItemHolder, position: Int) {
        holder.bind(getItem(position))

    }


}

class FilmItemHolder(container: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(container.context)
        .inflate(R.layout.recycler_view_item, container, false)
) {
    fun bind(item: ItemDataModel) {
        itemView.apply {
            tvItemName.text = item.name
            tvDataShowing.text = item.firstAirDate

            val voteAverage = "Average ${item.voteAverage}/10"
            tvItemVoteAverage.text = voteAverage

            val voteCount = "Votes: ${item.voteCount}"
            tvItemVoteCount.text = voteCount

            Glide.with(itemView.context)
                .load(IMAGE_BASE_URL + item.posterPath)
                .error(R.drawable.error_outline_48)
                .into(itemView.ivItemImage)

        }
        itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(NAME, item.name)
            bundle.putString(FIRST_AIR_DATE, item.firstAirDate)
            bundle.putString(POSTER_PATH, item.posterPath)
            bundle.putString(VOTE_AVERAGE, item.voteAverage)
            bundle.putString(VOTE_COUNT, item.voteCount)
            bundle.putString(ORIGINAL_NAME, item.originalName)
            bundle.putString(OVERVIEW, item.overview)
            (itemView.context as MainActivity).navController
                .navigate(R.id.action_mainFragment_to_detailsFragment, args = bundle)
        }
    }

    companion object {
        const val NAME = "name"
        const val FIRST_AIR_DATE = "firstAirDate"
        const val POSTER_PATH = "posterPath"
        const val VOTE_AVERAGE = "voteAverage"
        const val VOTE_COUNT = "voteCount"
        const val ORIGINAL_NAME = "originalName"
        const val OVERVIEW = "overview"
    }
}