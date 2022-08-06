package com.test

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import com.test.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val supportMapFragment =
            supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        supportMapFragment.getMapAsync(this)


    }

    val url =
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTDwUqi1fgtd8cnS2eXDzdC5qrDoGgj65xfptc3-yc&s"

    @SuppressLint("CheckResult")
    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        googleMap.getUiSettings().isZoomControlsEnabled = true
        val latlong = LatLng(12.9716, 77.5946)
        googleMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                latlong, 9.0f
            )
        )

        /*Handler(Looper.myLooper()!!).postDelayed(Runnable {
            loadImageOnMap(latlong, googleMap)
        }, 2000)

        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            loadImageOnMap(LatLng(12.87, 77.65), googleMap)
        }, 4000)

        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            loadImageOnMap(LatLng(12.67, 77.65), googleMap)
        }, 5000)

        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            loadImageOnMap(LatLng(12.87, 77.58), googleMap)
        }, 7000)*/

        loadImageUsingPicassco(latlong, googleMap)


    }

    private fun loadImageOnMap(latlong: LatLng, googleMap: GoogleMap) {
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap?>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap?>?
                ) {
                    binding.image.setImageBitmap(resource)
                    googleMap.addMarker(
                        MarkerOptions().position(latlong)
                            .icon(BitmapDescriptorFactory.fromBitmap(resource))
                    )
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    Log.i("GLIDE", "onLoadCleared: ")
                }
            }
            )
    }

    private fun loadImageUsingPicassco(latlong: LatLng, googleMap: GoogleMap) {
        Picasso.get()
            .load(url)
            .into(object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    Log.i(TAG, "onBitmapLoaded: ")
                    googleMap.addMarker(
                        MarkerOptions().position(latlong)
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap!!))
                    )
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    Log.i("PICASSCO", "onBitmapFailed: ")
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    Log.i("PICASSCO", "onPrepareLoad: ")
                }

            })
    }

}