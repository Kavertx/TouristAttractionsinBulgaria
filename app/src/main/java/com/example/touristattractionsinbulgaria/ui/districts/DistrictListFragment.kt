package com.example.touristattractionsinbulgaria.ui.districts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.touristattractionsinbulgaria.TouristAttractionApplication
import com.example.touristattractionsinbulgaria.databinding.FragmentDistrictListBinding
import com.example.touristattractionsinbulgaria.ui.attractions.AttractionListAdapter
import com.example.touristattractionsinbulgaria.ui.attractions.AttractionListFragmentDirections

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = DistrictListAdapter {
            val action = DistrictListFragmentDirections.actionDistrictListFragmentToDistrictFragment(it.id)
            this.findNavController().navigate(action)
        }

        viewModel.allDistricts.observe(this.viewLifecycleOwner){ districts->
            districts.let {
                adapter.submitList(it)
            }
        }
        binding.districtListRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.districtListRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}