package com.example.touristattractionsinbulgaria.ui.attractions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.touristattractionsinbulgaria.R
import com.example.touristattractionsinbulgaria.data.models.AttractionWithImages
import com.example.touristattractionsinbulgaria.databinding.ItemAttractionBinding

class AttractionListAdapter(private val onClicked: (AttractionWithImages) -> Unit) :
    ListAdapter<AttractionWithImages, AttractionListAdapter.AttractionViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        return AttractionViewHolder(
            ItemAttractionBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onClicked(current)
        }
        holder.bind(current)
    }

    class AttractionViewHolder(private var binding: ItemAttractionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(attraction: AttractionWithImages) {
            binding.itemAttractionName.text = attraction.attraction.attractionName
            binding.itemAttractionDescription.text = attraction.attraction.description
            if (attraction.images.isNotEmpty()) {
                Glide
                    .with(binding.root.context)
                    .load(attraction.images.first().imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.loading_animation)
                    .into(binding.itemImageView)
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<AttractionWithImages>() {
            override fun areItemsTheSame(
                oldAttraction: AttractionWithImages,
                newAttraction: AttractionWithImages
            ): Boolean {
                return oldAttraction === newAttraction
            }

            override fun areContentsTheSame(
                oldAttraction: AttractionWithImages,
                newAttraction: AttractionWithImages
            ): Boolean {
                return oldAttraction.attraction.attractionName == newAttraction.attraction.attractionName
            }
        }
    }
}