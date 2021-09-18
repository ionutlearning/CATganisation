package com.example.catganisation.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.catganisation.databinding.BreedItemBinding
import com.example.catganisation.domain.model.Breed
import com.example.catganisation.presentation.ui.util.loadImage

class BreedsAdapter(private val onClickListener: (String) -> Unit) :
    ListAdapter<Breed, BreedsAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(
        private val binding: BreedItemBinding,
        private val onClickListener: (String) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun setup(breed: Breed) {
            with(binding) {
                image.loadImage(breed.imagePath)
                name.text = breed.name
                description.text = breed.description

                root.setOnClickListener { onClickListener(breed.id)
                println("aici123 clicked ${breed.roomId}")}
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =
            BreedItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(viewBinding, onClickListener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.setup(getItem(position))
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Breed>() {

    override fun areItemsTheSame(oldItem: Breed, newItem: Breed): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Breed, newItem: Breed): Boolean {
        return oldItem == newItem
    }
}