package hu.ait.weatherinfo

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import hu.ait.weatherinfo.adapter.CityAdapter
import hu.ait.weatherinfo.data.City
import hu.ait.weatherinfo.databinding.ActivityScrollingBinding
import hu.ait.weatherinfo.dialog.CityDialog

class ScrollingActivity : AppCompatActivity(), CityDialog.CityHandler {

    private lateinit var binding: ActivityScrollingBinding
    private lateinit var adapter: CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
//            adapter.addCity(City("Demo"))
            CityDialog().show(supportFragmentManager,"CITY_DIALOG")
        } 

        adapter = CityAdapter(this)
        binding.recyclerCity.adapter = adapter
    }

    override fun cityCreated(city: City) {
        adapter.addCity(city)
    }

}