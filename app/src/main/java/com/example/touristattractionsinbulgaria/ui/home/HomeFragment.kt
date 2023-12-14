package com.example.touristattractionsinbulgaria.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.touristattractionsinbulgaria.TouristAttractionApplication
import com.example.touristattractionsinbulgaria.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by activityViewModels {
        HomeViewModelFactory(
            (activity?.application as TouristAttractionApplication).database.districtDao(),
            (activity?.application as TouristAttractionApplication).database.attractionDao(),
            (activity?.application as TouristAttractionApplication).database.imageDao()
        )
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)


//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}