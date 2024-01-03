package com.example.touristattractionsinbulgaria.ui.attractions

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.touristattractionsinbulgaria.TouristAttractionApplication
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.touristattractionsinbulgaria.databinding.FragmentAttractionListBinding

class AttractionListFragment : Fragment() {

    private var _binding: FragmentAttractionListBinding? = null
    private val viewModel: AttractionListViewModel by activityViewModels {
        AttractionListViewModelFactory(
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
        _binding = FragmentAttractionListBinding.inflate(inflater, container, false)
        //TODO:something is happening on the main thread? need to fix
        val adapter = AttractionListAdapter {
            val action = AttractionListFragmentDirections.actionAttractionListFragmentToAttractionFragment(it.id)
            this.findNavController().navigate(action)
        }
        viewModel.setAttractionList()
        binding.attractionListRecyclerView.adapter = adapter
        viewModel.allAttractions.observe(this.viewLifecycleOwner){ attractionsCurr->
            attractionsCurr.let {
                adapter.submitList(it)
                Log.d("observe", "submit")
            }
        }
        binding.attractionListRecyclerView.layoutManager = GridLayoutManager(context,2)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}