package com.example.catganisation.presentation.breeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.catganisation.databinding.FragmentBreedsBinding
import com.example.catganisation.domain.ViewResult
import com.example.catganisation.domain.entities.FilterEntity
import com.example.catganisation.presentation.breeds.BreedsViewModel.Companion.ALL_BREEDS
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
        viewModel.getBreeds()
        val adapter = BreedsAdapter(::navigateToDetails)
        binding.breeds.adapter = adapter

        subscribeUi(adapter)

        return binding.root
    }

    private fun navigateToDetails(id: String) {
        findNavController().navigate(
            BreedsFragmentDirections.actionBreedsFragmentToDetailsFragment(
                id
            )
        )
    }

    private fun subscribeUi(adapter: BreedsAdapter) {
        viewModel.viewState.observe(viewLifecycleOwner, { state ->
            binding.progressBar.visibility = if (state is ViewResult.Loading) VISIBLE else GONE
            binding.errorMessage.visibility = if (state is ViewResult.Error) VISIBLE else GONE
            binding.breedsContainer.visibility = if (state is ViewResult.Success) VISIBLE else GONE

            when (state) {
                is ViewResult.Success -> {
                    adapter.submitList(state.data.breeds)
                    if (!state.data.isFiltering) {
                        setupFilterMenu(state.data.filters)
                    }
                    binding.filterTitle.text = state.data.filter
                }
                is ViewResult.Error -> binding.errorMessage.text = state.message
            }
        })
    }

    private fun setupFilterMenu(filters: List<FilterEntity>) {
        val popupMenu = PopupMenu(requireContext(), binding.filterMenu)
        popupMenu.menu.add(ALL_BREEDS)
        filters.forEach { filter ->
            popupMenu.menu.add(filter.name)
        }
        popupMenu.setOnMenuItemClickListener { item ->
            viewModel.filter(item.title.toString())
            false
        }

        binding.filterMenu.setOnClickListener { popupMenu.show() }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
