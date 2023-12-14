package com.example.touristattractionsinbulgaria.ui.districts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.touristattractionsinbulgaria.TouristAttractionApplication
import com.example.touristattractionsinbulgaria.databinding.FragmentDistrictListBinding

class DistrictListFragment : Fragment() {

    private var _binding: FragmentDistrictListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: DistrictListViewModel by activityViewModels {
        DistrictListViewModelFactory(
            (activity?.application as TouristAttractionApplication).database.districtDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDistrictListBinding.inflate(inflater, container, false)


//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}