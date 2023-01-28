package com.example.weathermvvm.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weathermvvm.databinding.FavoriteLocationsListItemBinding
import com.example.weathermvvm.domain.model.favorite.FavoritePlaces
import com.example.weathermvvm.presentation.ui.adapter.features.OnItemClickListener

class FavoriteLocationsRwAdapter :
    RecyclerView.Adapter<FavoriteLocationsRwAdapter.FavoriteLocationsHolder>() {

    private lateinit var mListener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    class FavoriteLocationsHolder(
        binding: FavoriteLocationsListItemBinding,
        clickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        val item = binding.cardView
        val locationName = binding.tvPlaceName

        init {
            item.setOnClickListener {
                clickListener.onItemClick(locationName.text.toString())
            }
        }

    }

    private var response = mutableListOf<FavoritePlaces>()

    fun setResponse(favoritePlaces: List<FavoritePlaces>) {
        response = favoritePlaces.toMutableList()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FavoriteLocationsHolder {
        val binding =
            FavoriteLocationsListItemBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        return FavoriteLocationsHolder(binding, mListener)
    }

    override fun getItemCount(): Int = response.size

    override fun onBindViewHolder(holder: FavoriteLocationsHolder, position: Int) {
        val favoritePlaces = response[position]
        holder.locationName.text = favoritePlaces.placeName
    }


}