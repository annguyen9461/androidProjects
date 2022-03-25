package hu.ait.andwallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ait.andwallet.databinding.ActivitySummaryBinding

class SummaryActivity : AppCompatActivity() {
    lateinit var binding: ActivitySummaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}