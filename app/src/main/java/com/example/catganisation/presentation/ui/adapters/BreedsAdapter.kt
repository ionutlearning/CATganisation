package com.example.catganisation.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catganisation.R
import com.example.catganisation.domain.model.Breed
import com.example.catganisation.presentation.ui.util.loadImage

class BreedsAdapter(private val breeds: List<Breed>) :
    RecyclerView.Adapter<BreedsAdapter.ViewHolder>() {

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun setup(breed: Breed) {
            view.findViewById<ImageView>(R.id.image).loadImage(breed.imagePath)
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