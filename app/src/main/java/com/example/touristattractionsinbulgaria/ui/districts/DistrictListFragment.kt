package com.example.touristattractionsinbulgaria.ui.districts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.touristattractionsinbulgaria.TouristAttractionApplication
import com.example.touristattractionsinbulgaria.databinding.FragmentDistrictListBinding

class DistrictListFragment : Fragment() {

    private var _binding: FragmentDistrictListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: DistrictViewModel by activityViewModels {
        DistrictViewModelFactory(
            (activity?.application as TouristAttractionApplication).database.districtDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDistrictListBinding.inflate(inflater, container, false)
        viewModel.setDistrictList()
        val adapter = DistrictListAdapter {
            val action =
                DistrictListFragmentDirections.actionDistrictListFragmentToDistrictFragment(it.id)
            this.findNavController().navigate(action)
        }
        viewModel.allDistricts.observe(this.viewLifecycleOwner) { districtsCurr ->
            districtsCurr.let {
                adapter.submitList(it)
                //This logging triggers twice on all but the first time creating the fragment,
                // but the fragment is not being destroyed between the double logs
                Log.d("adapterSize", "${it?.size}")
            }
        }
        binding.districtListRecyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.districtListRecyclerView.adapter = adapter
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("RIP", "Deadge")
        _binding = null
    }
}