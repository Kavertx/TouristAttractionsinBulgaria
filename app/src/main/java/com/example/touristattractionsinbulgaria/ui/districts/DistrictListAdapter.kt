package com.example.touristattractionsinbulgaria.ui.districts

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.touristattractionsinbulgaria.R
import com.example.touristattractionsinbulgaria.data.Attraction
import com.example.touristattractionsinbulgaria.data.District
import com.example.touristattractionsinbulgaria.data.RoomDb
import com.example.touristattractionsinbulgaria.databinding.ItemAttractionBinding
import com.example.touristattractionsinbulgaria.databinding.ItemDistrictBinding

class DistrictListAdapter(private val onClicked: (District) -> Unit) :
    ListAdapter<District, DistrictListAdapter.DistrictViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistrictViewHolder {
        return DistrictViewHolder(
            ItemDistrictBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: DistrictViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
        holder.itemView.setOnClickListener {
            onClicked(current)
        }
    }

    class DistrictViewHolder(private var binding: ItemDistrictBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(district: District) {
            binding.itemDistrictName.text = district.districtName
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<District>() {
            override fun areItemsTheSame(
                oldDistrict: District,
                newDistrict: District
            ): Boolean {
                return oldDistrict === newDistrict
            }

            override fun areContentsTheSame(
                oldDistrict: District,
                newDistrict: District
            ): Boolean {
                return oldDistrict.districtName == newDistrict.districtName
            }
        }
    }
}