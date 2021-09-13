package com.example.catganisation.presentation.ui.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catganisation.R
import com.example.catganisation.domain.model.Breed

class BreedsAdapter(private val breeds: List<Breed>) :
    RecyclerView.Adapter<BreedsAdapter.ViewHolder>() {

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun setup(breed: Breed) {
            val imageView = view.findViewById<ImageView>(R.id.image)
            Glide.with(view.context)
                .load(Uri.parse(breed.imagePath))
                .centerCrop()
                .into(imageView)

            view.findViewById<TextView>(R.id.name).text = breed.name

            view.findViewById<TextView>(R.id.description).text = breed.description
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.breed_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.setup(breeds[position])
    }

    override fun getItemCount() = breeds.size

}