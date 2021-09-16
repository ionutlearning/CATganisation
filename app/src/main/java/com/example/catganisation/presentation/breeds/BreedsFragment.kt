package com.example.catganisation.presentation.breeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.catganisation.databinding.FragmentBreedsBinding
import com.example.catganisation.domain.NetworkResult
import com.example.catganisation.presentation.ui.adapters.BreedsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedsFragment : Fragment() {

    private val viewModel: BreedsViewModel by viewModels()
    private var _binding: FragmentBreedsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreedsBinding.inflate(inflater, container, false)
        val adapter = BreedsAdapter()
        binding.breeds.adapter = adapter

        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi(adapter: BreedsAdapter) {
        viewModel.state.observe(viewLifecycleOwner, { state ->
            binding.progressBar.visibility = if (state is NetworkResult.Loading) VISIBLE else GONE
            binding.errorMessage.visibility = if (state is NetworkResult.Error) VISIBLE else GONE
            binding.breeds.visibility = if (state is NetworkResult.Success) VISIBLE else GONE

            when (state) {
                is NetworkResult.Success -> adapter.submitList(state.data)
                is NetworkResult.Error -> binding.errorMessage.text = state.message
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
