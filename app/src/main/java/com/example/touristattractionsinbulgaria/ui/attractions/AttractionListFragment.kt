package com.example.touristattractionsinbulgaria.ui.attractions

import android.os.Bundle
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AttractionListAdapter {
            val action = AttractionListFragmentDirections.actionAttractionListFragmentToAttractionFragment(it.id)
            this.findNavController().navigate(action)
        }
        binding.attractionListRecyclerView.adapter = adapter
        viewModel.allAttractions.observe(this.viewLifecycleOwner){ attractions->
            attractions.let {
                adapter.submitList(it)
            }
        }
        binding.attractionListRecyclerView.layoutManager = GridLayoutManager(context,2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}