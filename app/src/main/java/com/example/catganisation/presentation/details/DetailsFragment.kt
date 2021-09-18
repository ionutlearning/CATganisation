package com.example.catganisation.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.catganisation.databinding.FragmentDetailsBinding
import com.example.catganisation.domain.ViewResult
import com.example.catganisation.domain.entities.BreedEntity
import com.example.catganisation.presentation.ui.util.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        subscribeUi()

        val id = args.id
        viewModel.getBreedById(id)

        return binding.root
    }

    private fun subscribeUi() {
        viewModel.viewState.observe(viewLifecycleOwner, { state ->
            binding.progressBar.visibility = if (state is ViewResult.Loading) VISIBLE else GONE
            binding.errorMessage.visibility = if (state is ViewResult.Error) VISIBLE else GONE

            when (state) {
                is ViewResult.Success -> setupView(state.data)
                is ViewResult.Error -> binding.errorMessage.text = state.message
            }
        })
    }

    private fun setupView(breed: BreedEntity) {
        with(binding) {
            name.text = breed.name
            description.text = breed.description
            temperament.text = breed.temperament
            link.text = breed.link
            origin.text = breed.origin
            image.loadImage(breed.imagePath)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
