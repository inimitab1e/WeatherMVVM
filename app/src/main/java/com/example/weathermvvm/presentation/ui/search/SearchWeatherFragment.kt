package com.example.weathermvvm.presentation.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weathermvvm.R
import com.example.weathermvvm.databinding.FragmentSearchWeatherBinding
import com.example.weathermvvm.extensions.onTextChange
import com.example.weathermvvm.presentation.ui.adapter.SearchWeatherRwAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SearchWeatherFragment : Fragment() {
    private var binding: FragmentSearchWeatherBinding? = null
    private val viewModelSearch: SearchWeatherViewModel by viewModels()
    private val searchWeatherRwAdapter: SearchWeatherRwAdapter by lazy {
        SearchWeatherRwAdapter()
    }

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
        binding?.rwWeather?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = searchWeatherRwAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        binding?.apply {
            searchLocationField.onTextChange { query ->
                viewModelSearch.getResponse(query)
                progressBar.isVisible = true
                rwWeather.isGone = true
            }
        }

        viewModelSearch.weatherOnSuccessResponse.observe(viewLifecycleOwner) { response ->
            if (response != null) {
                with(searchWeatherRwAdapter) {
                    setResponse(response)
                    notifyDataSetChanged()
                }
                binding?.apply {
                    progressBar.isGone = true
                    rwWeather.isVisible = true
                }
            } else {
                binding?.apply {
                    test.text = "Unknown place :("
                    progressBar.isGone = true
                    rwWeather.isGone = true
                }
            }
        }

        if (binding?.test?.text?.isEmpty() == true) {
            binding?.rwWeather?.isGone = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}