package com.example.weathermvvm.presentation.ui.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weathermvvm.R
import com.example.weathermvvm.databinding.FragmentFavoriteLocationsBinding
import com.example.weathermvvm.domain.model.favorite.FavoritePlaces
import com.example.weathermvvm.presentation.ui.adapter.FavoriteLocationsRwAdapter
import com.example.weathermvvm.presentation.ui.adapter.features.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class FavouriteLocationsFragment : Fragment(R.layout.fragment_favorite_locations) {

    private val binding by viewBinding(FragmentFavoriteLocationsBinding::bind)
    private val favoriteLocationsViewModel: FavoriteLocationsViewModel by viewModels()
    private val favoriteLocationsRwAdapter: FavoriteLocationsRwAdapter by lazy {
        FavoriteLocationsRwAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteLocationsViewModel.getListOfFavoritePlaces()

        binding.rwFavoriteLocations.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = favoriteLocationsRwAdapter
        }
    }

    override fun onResume() {
        super.onResume()

        favoriteLocationsViewModel.listOfFavorites.observe(viewLifecycleOwner) { listOfFavoritePlaces ->
            if (listOfFavoritePlaces.isNotEmpty()) {
                setupList(listOfFavoritePlaces)
            } else {
                if (favoriteLocationsRwAdapter.itemCount == 0) {
                    binding.listEmptyNotification.isVisible = true
                }
            }
        }

        favoriteLocationsRwAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(name: String) {
                Timber.e("value: %s", name)
            }
        })
    }

    private fun setupList(listOfFavoritePlaces: List<FavoritePlaces>) {
        binding.listEmptyNotification.isGone = true
        with(favoriteLocationsRwAdapter) {
            setResponse(listOfFavoritePlaces)
            notifyDataSetChanged()
        }
    }
}