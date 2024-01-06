package com.example.touristattractionsinbulgaria.ui.districts

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.touristattractionsinbulgaria.TouristAttractionApplication
import com.example.touristattractionsinbulgaria.data.District
import com.example.touristattractionsinbulgaria.databinding.FragmentDistrictBinding

class DistrictFragment : Fragment() {

    private var _binding: FragmentDistrictBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: DistrictViewModel by activityViewModels {
        DistrictViewModelFactory(
            (activity?.application as TouristAttractionApplication).database.districtDao()
        )
    }
    private val navigationArgs: DistrictFragmentArgs by navArgs()
    private lateinit var district: District
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDistrictBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun bind(district: District){
        binding.apply {
            districtName.text = district.districtName
            districtDescription.text = district.districtDescription
            districtDescription.movementMethod = ScrollingMovementMethod.getInstance()
//            attractionsInDistrict.setOnClickListener {
//                val action = DistrictFragmentDirections.actionDistrictFragmentToAttractionListFragment(district.id)
//                findNavController().navigate(action)
//            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSelectedDistrict(navigationArgs.districtId)
        viewModel.selectedDistrict.observe(this.viewLifecycleOwner){ selectedDistrict->
            district = selectedDistrict
            bind(district)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}