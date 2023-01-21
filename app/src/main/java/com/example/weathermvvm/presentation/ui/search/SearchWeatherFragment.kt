package com.example.weathermvvm.presentation.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.weathermvvm.R
import com.example.weathermvvm.databinding.FragmentSearchWeatherBinding
import com.example.weathermvvm.extensions.onTextChange
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchWeatherFragment : Fragment() {
    private var binding: FragmentSearchWeatherBinding? = null
    private val viewModelSearch: SearchWeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSearchWeatherBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lifecycleScope.launchWhenStarted {
            with(binding!!) {
                searchLocationField.onTextChange { query ->
                    viewModelSearch.getResponse(query)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}