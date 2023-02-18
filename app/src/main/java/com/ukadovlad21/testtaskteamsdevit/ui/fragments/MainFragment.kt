package com.ukadovlad21.testtaskteamsdevit.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.ukadovlad21.testtaskteamsdevit.R
import com.ukadovlad21.testtaskteamsdevit.mapper.ResponseMapper
import com.ukadovlad21.testtaskteamsdevit.ui.activity.MainActivity
import com.ukadovlad21.testtaskteamsdevit.ui.adapter.FilmAdapter
import com.ukadovlad21.testtaskteamsdevit.utils.Resource
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel by lazy {
        (activity as MainActivity).mainViewModel
    }

    private val filmAdapter = FilmAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()


        viewModel.getAllByPage(1).observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideLoadingBar()
                    response.data.let { responses ->
                        val listOfItem = ResponseMapper().map(responses)
                        filmAdapter.submitList(listOfItem)

                    }
                }
                is Resource.Error -> {
                    Toast.makeText(activity, response.message, Toast.LENGTH_LONG).show()

                }
                is Resource.Loading -> showLoadingBar()

            }
        }

    }

    private fun hideLoadingBar() {
        loadingProgressBar.visibility = View.GONE
        rvList.visibility = View.VISIBLE
    }

    private fun showLoadingBar() {
        loadingProgressBar.visibility = View.VISIBLE
        rvList.visibility = View.GONE
    }

    private fun setupRecyclerView() {
        rvList.apply {
            adapter = filmAdapter
            layoutManager = GridLayoutManager(requireContext(),3)
        }
    }

}