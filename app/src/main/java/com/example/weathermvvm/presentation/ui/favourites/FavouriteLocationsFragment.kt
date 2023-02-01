package com.example.weathermvvm.presentation.ui.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weathermvvm.R
import com.example.weathermvvm.databinding.FragmentFavoriteLocationsBinding
import com.example.weathermvvm.domain.model.favorite.FavoritePlaces
import com.example.weathermvvm.presentation.ui.MainActivityViewModel
import com.example.weathermvvm.presentation.ui.adapter.FavoriteLocationsRwAdapter
import com.example.weathermvvm.presentation.ui.adapter.features.OnItemClickListener
import com.example.weathermvvm.presentation.ui.adapter.features.SwipeToDeleteCallback
import com.example.weathermvvm.utils.StringConstants
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteLocationsFragment : Fragment(R.layout.fragment_favorite_locations) {

    private val binding by viewBinding(FragmentFavoriteLocationsBinding::bind)
    private val favoriteLocationsViewModel: FavoriteLocationsViewModel by viewModels()
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    private val favoriteLocationsRwAdapter: FavoriteLocationsRwAdapter by lazy {
        FavoriteLocationsRwAdapter()
    }
    private var listOfFavoritePlaces = mutableListOf<FavoritePlaces>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteLocationsViewModel.getListOfFavoritePlaces()

        binding.rwFavoriteLocations.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = favoriteLocationsRwAdapter
        }

        setupRecyclerViewFeatures()
    }

    private fun setupRecyclerViewFeatures() {
        favoriteLocationsRwAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(name: String) {
                mainActivityViewModel.setFavoriteLocationNameToShare(locationName = name)
                findNavController().navigate(R.id.action_favouriteLocationsFragment_to_searchWeatherFragment)
            }
        })

        val swipeHandler = object : SwipeToDeleteCallback(requireActivity()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val locationNameToDelete = favoriteLocationsRwAdapter.getItem(position).placeName
                val latitude = favoriteLocationsRwAdapter.getItem(position).latitude
                val longitute = favoriteLocationsRwAdapter.getItem(position).longitude
                val deletedItem = FavoritePlaces(
                    placeName = locationNameToDelete,
                    latitude = latitude,
                    longitude = longitute
                )
                favoriteLocationsViewModel.deletePlaceFromListOfFavorites(locationNameToDelete!!)
                favoriteLocationsRwAdapter.removeAt(position)
                if (favoriteLocationsRwAdapter.itemCount == 0) {
                    binding.listEmptyNotification.isVisible = true
                }

                Snackbar.make(
                    binding.rwFavoriteLocations,
                    StringConstants.deletedSnackBarNotify + locationNameToDelete,
                    Snackbar.LENGTH_LONG
                )
                    .setAction(
                        StringConstants.undo,
                        View.OnClickListener {
                            addDeletedItemBack(
                                name = locationNameToDelete,
                                latitude = latitude!!,
                                longitude = longitute!!
                            )
                            listOfFavoritePlaces.add(position, deletedItem)
                            setupRecyclerViewList(listOfFavoritePlaces)
                            favoriteLocationsRwAdapter.notifyItemInserted(position)
                        }).show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.rwFavoriteLocations)
    }

    private fun addDeletedItemBack(name: String, latitude: Double, longitude: Double) {
        favoriteLocationsViewModel.addToFavorite(name, latitude, longitude)
    }

    override fun onResume() {
        super.onResume()

        favoriteLocationsViewModel.listOfFavorites.observe(viewLifecycleOwner) { listOfFavorites ->
            if (listOfFavorites.isNotEmpty()) {
                listOfFavoritePlaces = listOfFavorites
                setupRecyclerViewList(listOfFavoritePlaces)
            } else {
                if (favoriteLocationsRwAdapter.itemCount == 0) {
                    binding.listEmptyNotification.isVisible = true
                }
            }
        }
    }

    private fun setupRecyclerViewList(listOfFavoritePlaces: MutableList<FavoritePlaces>) {
        binding.listEmptyNotification.isGone = true
        with(favoriteLocationsRwAdapter) {
            setResponse(listOfFavoritePlaces)
            notifyDataSetChanged()
        }
    }
}