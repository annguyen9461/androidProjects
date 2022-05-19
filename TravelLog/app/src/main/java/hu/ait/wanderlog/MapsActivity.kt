package hu.ait.wanderlog

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import hu.ait.wanderlog.databinding.ActivityMapsBinding
import java.util.*
import kotlin.concurrent.thread


class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener,
    MyLocationManager.OnNewLocationAvailable {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private lateinit var myLocationManager: MyLocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myLocationManager = MyLocationManager(this, this)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onStop() {
        super.onStop()
        myLocationManager.stopLocationMonitoring()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(this,
            R.raw.mapstyle))

        mMap.isTrafficEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true

        mMap.setOnMapClickListener {
            val marker = mMap.addMarker(
                MarkerOptions()
                    .position(it)
            )
            marker!!.isDraggable = false
        }

        val budapestLoc = LatLng(47.4979,19.0402)
        val budapest = mMap.addMarker(
            MarkerOptions()
                .position(budapestLoc)
                .title("Budapest")
        )

        val viennaLoc = LatLng(48.2082,16.3738)
        val vienna = mMap.addMarker(
            MarkerOptions()
                .position(viennaLoc)
                .title("Vienna")
        )

        val veniceLoc = LatLng(45.4408,12.3155)
        val venice = mMap.addMarker(
            MarkerOptions()
                .position(veniceLoc)
                .title("Venice")
        )

        val sydney = LatLng(-33.852, 151.211)
        mMap.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Sydney")
        )

        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this);
    }

    /** Called when the user clicks a marker.  */
    override fun onMarkerClick(marker: Marker): Boolean {
//        val intentNotes = Intent(this, MarkerDetails::class.java)
//        intentNotes.putExtra("KEY_MARKER",marker.title)
//
//        startActivity(intentNotes)

        startActivity(Intent(this, MarkerDetails::class.java))

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return true
    }

    override fun onNewLocation(location: Location) {
        thread {
            try {
                val gc = Geocoder(this, Locale.getDefault())
                var addrs: List<Address> =
                    gc.getFromLocation(location.latitude, location.longitude, 3)
                val addr =
                    "${addrs[0].getAddressLine(0)}, ${addrs[0].getAddressLine(1)}"

                runOnUiThread {
                    var marker = mMap.addMarker(
                        MarkerOptions()
                            .position(LatLng(location.latitude, location.longitude))
                            .title(addr)
                            .snippet(addr)
                    )
                    marker!!.showInfoWindow();
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@MapsActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}