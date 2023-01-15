package com.example.weathermvvm.presentation.ui.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weathermvvm.R
import com.example.weathermvvm.databinding.FragmentChooseLocationOnMapBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseLocationOnMapFragment : Fragment() {
    private var binding: FragmentChooseLocationOnMapBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChooseLocationOnMapBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}