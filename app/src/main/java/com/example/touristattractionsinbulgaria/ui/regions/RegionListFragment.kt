package com.example.touristattractionsinbulgaria.ui.regions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.touristattractionsinbulgaria.databinding.FragmentRegionListBinding

class RegionListFragment : Fragment() {

    private var _binding: FragmentRegionListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(RegionsViewModel::class.java)

        _binding = FragmentRegionListBinding.inflate(inflater, container, false)
        val root: View = binding.root


//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}