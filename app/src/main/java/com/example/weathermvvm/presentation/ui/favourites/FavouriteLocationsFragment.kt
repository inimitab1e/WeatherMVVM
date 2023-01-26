package com.example.weathermvvm.presentation.ui.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weathermvvm.R
import com.example.weathermvvm.databinding.FragmentFavouriteLocationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteLocationsFragment : Fragment(R.layout.fragment_favourite_locations) {

    private val binding by viewBinding(FragmentFavouriteLocationsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}