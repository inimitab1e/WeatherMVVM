package com.example.weathermvvm.presentation.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.example.weathermvvm.databinding.FragmentChooseLocationOnMapBinding
import dagger.hilt.android.AndroidEntryPoint
import org.osmdroid.config.Configuration.*
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Overlay
import org.osmdroid.views.overlay.OverlayItem
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay


@AndroidEntryPoint
class ChooseLocationOnMapFragment : Fragment() {
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 1
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

        val ctx = requireActivity().applicationContext
        getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx))

        val items = ArrayList<OverlayItem>()
        items.add(OverlayItem("Title", "Description", GeoPoint(48.8583, 2.2944)))

        binding?.apply {
            with(vMap) {
                setMultiTouchControls(true)
                post {
                    setTileSource(TileSourceFactory.MAPNIK)
                    controller.setZoom(9.5)
                    controller.setCenter(GeoPoint(48.8583, 2.2944))
                }
            }
        }


    }

    fun addMarker(center: GeoPoint?) {
        val map = binding?.vMap
        val marker = Marker(map)
        marker.position = center
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        map?.getOverlays()?.clear()
        map?.getOverlays()?.add(marker)
        map?.invalidate()
        marker.title = "Маркер"
    }

    override fun onResume() {
        super.onResume()
        binding?.vMap?.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding?.vMap?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
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