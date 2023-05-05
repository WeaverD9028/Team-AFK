package com.example.autoaid


import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
import androidx.drawerlayout.widget.DrawerLayout
import com.example.autoaid.databinding.ActivityGoogleMapsBinding
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.model.*
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.google.android.material.navigation.NavigationView
import com.google.maps.GeoApiContext
import com.google.maps.PlacesApi
import com.google.maps.model.PlaceType
import kotlinx.android.synthetic.main.activity_diagnostic.*


class GoogleMapsActivity : AppCompatActivity(), OnMapReadyCallback,OnMyLocationButtonClickListener,OnRequestPermissionsResultCallback {



    // Start the autocomplete intent.

    private lateinit var placesClient: PlacesClient
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityGoogleMapsBinding
    private lateinit var currentLocation: Location
    private lateinit var LatitudeAndLongitude: com.google.android.gms.maps.model.LatLng
    //private val REQUEST_LOCATION_PERMISSION = 1
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val permissionCode = 101
    lateinit var  toggle : ActionBarDrawerToggle
    private lateinit var typefilter:TypeFilter
    val AUTOCOMPLETE_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var requestPermission: ActivityResultContracts.RequestPermission


        // Set the fields to specify which types of place data to
        // return after the user has made a selection.
        //val fields = listOf(Place.Field.ID, Place.Field.NAME)
        super.onCreate(savedInstanceState)

        binding = ActivityGoogleMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        //typefilter = AutocompleteFilter.builder().setType(AutocompleteFilter.TYPE_FILTER_ESTABLISHMENT)
        getUserCurrentLocation()




        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val navView : NavigationView = findViewById(R.id.nav_view)

        navView.setNavigationItemSelectedListener {

            when(it.itemId){

                R.id.nav_home -> Toast.makeText(applicationContext, "Click home", Toast.LENGTH_SHORT).show()
                R.id.nav_view -> Toast.makeText(applicationContext, "Click view", Toast.LENGTH_SHORT).show()
                R.id.nav_videos -> startActivity(Intent(this@GoogleMapsActivity,YouTubeActivity::class.java))
                R.id.nav_local -> startActivity(Intent(this@GoogleMapsActivity,GoogleMapsActivity::class.java))
                R.id.nav_share -> Toast.makeText(applicationContext, "Click share", Toast.LENGTH_SHORT).show()
                R.id.nav_review -> Toast.makeText(applicationContext, "Click review", Toast.LENGTH_SHORT).show()
                R.id.nav_setting -> startActivity(Intent(this@GoogleMapsActivity,SettingsActivity::class.java))
                R.id.nav_login -> Toast.makeText(applicationContext, "Click login", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    @SuppressLint("MissingPermission")
    private fun getUserCurrentLocation() {
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED) {
            // Request permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                permissionCode
            )
            return
        }


        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            // location could be null if the user turns location services even though the app has permission
            if (location != null) {
                //stores current location
                currentLocation = location
                Toast.makeText(
                    applicationContext,
                    currentLocation.latitude.toString() + "" + currentLocation.longitude.toString(),
                    Toast.LENGTH_LONG
                ).show()


                val mapFragment = supportFragmentManager
                    .findFragmentById(R.id.map) as SupportMapFragment
                mapFragment.getMapAsync(this)
            }

        }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            permissionCode -> if(grantResults.contains(PackageManager.PERMISSION_GRANTED))
                getUserCurrentLocation()
        }
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    data?.let {
                        val place = Autocomplete.getPlaceFromIntent(data)
                        Log.i(TAG, "Place: ${place.name}, ${place.id}")
                    }
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    // TODO: Handle the error.
                    data?.let {
                        val status = Autocomplete.getStatusFromIntent(data)
                        Log.i(TAG, status.statusMessage ?: "")
                    }
                }
                Activity.RESULT_CANCELED -> {
                    // The user canceled the operation.
                }
            }
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }*/










    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    override fun onMapReady(googleMap: GoogleMap) {


        val latLng = LatLng(currentLocation.latitude,currentLocation.longitude)
        val markerOptions = MarkerOptions().position(latLng).title("Current Location")
        mMap = googleMap
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 7f))
        mMap.addMarker(markerOptions)



        val context = GeoApiContext.Builder()
            .apiKey("AIzaSyCqOasqpH2C1yS5H_NYpflFSMTyoBgAtOk")
            .build()

        //com.google.maps.model.LatLng location = new com.google.maps.model.LatLng(currentLocation.latitude,currentLocation.longitude) // San Francisco coordinates
        val location = com.google.maps.model.LatLng(currentLocation.latitude, currentLocation.longitude)
        val radius = 5000 // Search radius in meters



        val response = PlacesApi.nearbySearchQuery(context,  location)
            .radius(radius)
            .type(PlaceType.CAR_REPAIR)
            .await()




        response.results.forEach {
            println(it.name)
            println(it.geometry)
            println(it.formattedAddress)
            println("Rating: ${it.rating}")
            println("-----")
            val shop1 = LatLng(32.52288888305427, -92.65078425501898)
            val shop2 = LatLng(32.52458957225824, -92.64267325536757)
            val shop3 = LatLng(32.52386587863411, -92.63808131305089)

            //val United = LatLng(37.41440870,-122.09053330)
            mMap.addMarker(MarkerOptions().position(shop1).title("Complete Oil Change" + " Rating: 4.6"))
            mMap.addMarker(MarkerOptions().position(shop2).title("Charlie's Auto Repair Shop" + " Rating: 4.3"))
            mMap.addMarker(MarkerOptions().position(shop3).title("BroomAuto" + " Rating: 4.6"))
        }




    }

    override fun onMyLocationButtonClick(): Boolean {
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){

            return true
        }
        return super.onOptionsItemSelected(item)


    }

}
