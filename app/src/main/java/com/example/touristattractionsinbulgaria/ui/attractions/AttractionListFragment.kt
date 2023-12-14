package com.example.touristattractionsinbulgaria.ui.attractions

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.touristattractionsinbulgaria.TouristAttractionApplication
import com.example.touristattractionsinbulgaria.databinding.FragmentAttractionListBinding
import com.example.touristattractionsinbulgaria.network.getAttractionDataWikiRequest
import com.example.touristattractionsinbulgaria.network.getAttractionImageFileWikiRequest
import kotlinx.coroutines.*

class AttractionListFragment : Fragment() {

    private var _binding: FragmentAttractionListBinding? = null
    private val viewModel: AttractionListViewModel by activityViewModels {
        AttractionListViewModelFactory((activity?.application as TouristAttractionApplication).database.attractionDao(),
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

        _binding = FragmentAttractionListBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}