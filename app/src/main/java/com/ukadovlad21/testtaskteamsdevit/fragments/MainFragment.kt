package com.ukadovlad21.testtaskteamsdevit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.ukadovlad21.testtaskteamsdevit.R
import com.ukadovlad21.testtaskteamsdevit.activity.MainActivity
import com.ukadovlad21.testtaskteamsdevit.utils.Resource
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel by lazy {
        (activity as MainActivity).mainViewModel
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllByPage(1).observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data.let { responses ->
//                        tvResult.text = responses.results[2].backdrop_path

                    }
                }
                is Resource.Error -> {
                    response.message.let { message ->
                        Toast.makeText(activity, "An error occurred: $message", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "wait", Toast.LENGTH_SHORT).show()
                }

            }
        }

    }

}