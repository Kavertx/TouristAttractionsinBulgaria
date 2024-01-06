package com.example.touristattractionsinbulgaria.ui.attractions

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.touristattractionsinbulgaria.R
import com.example.touristattractionsinbulgaria.TouristAttractionApplication
import com.example.touristattractionsinbulgaria.data.models.AttractionWithImages
import com.example.touristattractionsinbulgaria.databinding.FragmentAttractionBinding

class AttractionFragment : Fragment() {

    private var _binding: FragmentAttractionBinding? = null
    private val viewModel: AttractionViewModel by activityViewModels {
        AttractionViewModelFactory(
            (activity?.application as TouristAttractionApplication).database.attractionDao()
        )
    }
    lateinit var attraction: AttractionWithImages
    private val navigationArgs: AttractionFragmentArgs by navArgs()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private fun bind(attraction: AttractionWithImages) {
        binding.apply {
            attractionName.text = attraction.attraction.attractionName
            attractionDescription.text = attraction.attraction.description
            attractionDescription.scrollBarSize = 7
            attractionDescription.movementMethod = ScrollingMovementMethod.getInstance()
        }
        Glide
            .with(binding.root.context)
            .load(attraction.images.first().imageUrl)
            .centerCrop()
            .placeholder(R.drawable.loading_animation)
            .into(binding.imageView)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAttractionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setOneAttraction(navigationArgs.attractionId)
        viewModel.selectedAttraction.observe(this.viewLifecycleOwner) { selectedAttraction ->
            attraction = selectedAttraction
            bind(attraction)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}