package com.example.weathermvvm.presentation.ui.map

import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weathermvvm.R
import com.example.weathermvvm.databinding.FragmentChooseLocationOnMapBinding
import dagger.hilt.android.AndroidEntryPoint
import org.osmdroid.config.Configuration.*
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.OverlayItem


@AndroidEntryPoint
class ChooseLocationOnMapFragment : Fragment(R.layout.fragment_choose_location_on_map) {

    private val REQUEST_PERMISSIONS_REQUEST_CODE = 1
    private val binding by viewBinding(FragmentChooseLocationOnMapBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ctx = requireActivity().applicationContext
        getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx))

        val items = ArrayList<OverlayItem>()
        items.add(OverlayItem("Title", "Description", GeoPoint(48.8583, 2.2944)))

        with(binding.vMap) {
            setMultiTouchControls(true)
            post {
                setTileSource(TileSourceFactory.MAPNIK)
                controller.setZoom(9.5)
                controller.setCenter(GeoPoint(48.8583, 2.2944))
            }
        }
    }

    fun addMarker(center: GeoPoint) {
        val map = binding.vMap
        val marker = Marker(map)
        marker.position = center
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        map.getOverlays().clear()
        map.getOverlays().add(marker)
        map.invalidate()
        marker.title = "Маркер"
    }

    override fun onResume() {
        super.onResume()
        binding.vMap.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.vMap.onPause()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        val permissionsToRequest = ArrayList<String>()
        var i = 0
        while (i < grantResults.size) {
            permissionsToRequest.add(permissions[i])
            i++
        }
        val activity = requireActivity()
        if (permissionsToRequest.size > 0) {
            ActivityCompat.requestPermissions(
                activity,
                permissionsToRequest.toTypedArray(),
                REQUEST_PERMISSIONS_REQUEST_CODE
            )
        }
    }
}