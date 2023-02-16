package com.ukadovlad21.testtaskteamsdevit.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.bumptech.glide.Glide
import com.ukadovlad21.testtaskteamsdevit.R
import com.ukadovlad21.testtaskteamsdevit.ui.adapter.FilmItemHolder.Companion.FIRST_AIR_DATE
import com.ukadovlad21.testtaskteamsdevit.ui.adapter.FilmItemHolder.Companion.NAME
import com.ukadovlad21.testtaskteamsdevit.ui.adapter.FilmItemHolder.Companion.ORIGINAL_NAME
import com.ukadovlad21.testtaskteamsdevit.ui.adapter.FilmItemHolder.Companion.OVERVIEW
import com.ukadovlad21.testtaskteamsdevit.ui.adapter.FilmItemHolder.Companion.POSTER_PATH
import com.ukadovlad21.testtaskteamsdevit.ui.adapter.FilmItemHolder.Companion.VOTE_AVERAGE
import com.ukadovlad21.testtaskteamsdevit.ui.adapter.FilmItemHolder.Companion.VOTE_COUNT
import com.ukadovlad21.testtaskteamsdevit.utils.Constant
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment(R.layout.fragment_details) {

    val name by lazy { this.arguments?.get(NAME).toString() }
    val firstAirDate by lazy { this.arguments?.get(FIRST_AIR_DATE).toString() }
    val posterPath by lazy { this.arguments?.get(POSTER_PATH).toString() }
    val voteAverage by lazy { this.arguments?.get(VOTE_AVERAGE).toString() }
    val voteCount by lazy { this.arguments?.get(VOTE_COUNT).toString() }
    val originalName by lazy { this.arguments?.get(ORIGINAL_NAME).toString() }
    val overview by lazy {
        if (this.arguments?.get(OVERVIEW).toString().isEmpty()) "nothing..."
        else this.arguments?.get(OVERVIEW).toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupData()


    }

    private fun setupData() {
        Glide.with(requireContext())
            .load(Constant.IMAGE_BASE_URL + posterPath)
            .error(R.drawable.error_outline_48)
            .into(ivImage)

        tvName.text = name
        tvOriginalName.text = originalName

        tvAbout.text = overview
        tvVoteAverage.text = voteAverage

        val votesCountText = "Votes: $voteCount"
        tvVotesCount.text = votesCountText

        val firstAirDateTest = "Show date: $firstAirDate"
        tvFirstAirDate.text = firstAirDateTest

    }

}