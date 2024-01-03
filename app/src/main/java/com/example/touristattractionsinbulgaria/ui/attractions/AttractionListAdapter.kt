package com.example.touristattractionsinbulgaria.ui.attractions

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.touristattractionsinbulgaria.R
import com.example.touristattractionsinbulgaria.data.Attraction
import com.example.touristattractionsinbulgaria.data.RoomDb
import com.example.touristattractionsinbulgaria.databinding.ItemAttractionBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AttractionListAdapter(private val onClicked: (Attraction) -> Unit) :
    ListAdapter<Attraction, AttractionListAdapter.AttractionViewHolder>(DiffCallback) {

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
        private val imageDao = RoomDb.getDatabase(context = Application()).imageDao()
        //TODO: This looks suspicious as hell.
        // It probably would not work either.
        // Come back to it
        fun bind(attraction: Attraction) {
            var url: String?  = null
            GlobalScope.launch (Dispatchers.IO){
                url= imageDao.getOneImageForAttraction(attraction.id)
            }
           // val imgList = imageDao.getAllImagesForAttraction(attraction.id)
            binding.itemAttractionName.text = attraction.attractionName
            binding.itemAttractionDescription.text = attraction.description
            //bindImage(binding.itemImageView, imgList[1]?.imageUrl)
            if(url!=null) {
                Glide
                    .with(binding.root.context)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.drawable.loading_animation)
                    .into(binding.itemImageView)
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Attraction>() {
            override fun areItemsTheSame(
                oldAttraction: Attraction,
                newAttraction: Attraction
            ): Boolean {
                return oldAttraction === newAttraction
            }

            override fun areContentsTheSame(
                oldAttraction: Attraction,
                newAttraction: Attraction
            ): Boolean {
                return oldAttraction.attractionName == newAttraction.attractionName
            }
        }
    }
}