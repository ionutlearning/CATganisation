package com.example.catganisation.presentation.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.webkit.URLUtil
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
            origin.text = breed.origin
            image.loadImage(breed.imagePath)
        }

        setupLink(breed.link)
    }

    private fun setupLink(link: String) {
        with(binding.link) {
            text = link
            if (URLUtil.isValidUrl(link)) {
                this.setOnClickListener {
                    openUrlInBrowser(link)
                }
            }
        }
    }

    private fun openUrlInBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
