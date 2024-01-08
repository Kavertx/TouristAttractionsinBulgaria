package com.example.touristattractionsinbulgaria.ui.attractions

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.touristattractionsinbulgaria.TouristAttractionApplication
import com.example.touristattractionsinbulgaria.databinding.FragmentAttractionListBinding

class AttractionListFragment : Fragment() {

    private var _binding: FragmentAttractionListBinding? = null
    private val viewModel: AttractionViewModel by activityViewModels {
        AttractionViewModelFactory(
            (activity?.application as TouristAttractionApplication).database.attractionDao(),
        )
    }
    private val navigationArgs: AttractionListFragmentArgs by navArgs()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAttractionListBinding.inflate(inflater, container, false)
        val adapter = AttractionListAdapter {
            val action =
                AttractionListFragmentDirections.actionAttractionListFragmentToAttractionFragment(it.attraction.id)
            this.findNavController().navigate(action)
        }
        binding.attractionListRecyclerView.adapter = adapter
        if (!requireArguments().isEmpty) {
            Log.d("argument", requireArguments().toString())
            viewModel.setAttractionListWithDistrict(navigationArgs.districtId)
            viewModel.selectedDistrictAttractions.observe(this.viewLifecycleOwner) { attractionsCurr ->
                attractionsCurr.let {
                    adapter.submitList(it)
                    Log.d("attractionListSize", "${it.size}")
                }
            }
        } else {
            viewModel.setAttractionList()
            viewModel.allAttractions.observe(this.viewLifecycleOwner) { attractionsCurr ->
                attractionsCurr.let {
                    adapter.submitList(it)
                    Log.d("observe", "submit")
                }
            }
        }
        binding.attractionListRecyclerView.layoutManager = GridLayoutManager(context, 2)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}