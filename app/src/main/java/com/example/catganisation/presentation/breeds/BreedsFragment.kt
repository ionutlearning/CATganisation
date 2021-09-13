package com.example.catganisation.presentation.breeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.catganisation.R
import com.example.catganisation.domain.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedsFragment : Fragment() {

    private val viewModel: BreedsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_breeds, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, { state ->
            view.findViewById<ProgressBar>(R.id.progress_bar).visibility =
                if (state is NetworkResult.Loading) VISIBLE else GONE

            view.findViewById<TextView>(R.id.error_message).visibility =
                if (state is NetworkResult.Error) VISIBLE else GONE


            when (state) {
                is NetworkResult.Success -> view.findViewById<TextView>(R.id.breeds).text =
                    state.data.toString()
                is NetworkResult.Error ->
                    view.findViewById<TextView>(R.id.error_message).text = state.message
            }
        })
    }
}
