package com.example.touristattractionsinbulgaria.ui.attractions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.touristattractionsinbulgaria.databinding.FragmentAttractionBinding

class AttractionFragment  : Fragment() {

    private var _binding: FragmentAttractionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val attractionListViewModel =
            ViewModelProvider(this).get(AttractionViewModel::class.java)

        _binding = FragmentAttractionBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}