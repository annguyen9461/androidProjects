package hu.ait.weatherinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ait.weatherinfo.adapter.CityAdapter
import hu.ait.weatherinfo.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra(CityAdapter.KEY_DATA)) {
            binding.tvData.text = intent.getStringExtra(CityAdapter.KEY_DATA)
        }
    }
}