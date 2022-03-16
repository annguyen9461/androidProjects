package hu.ait.minesweeper

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar
import hu.ait.minesweeper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnReset.setOnClickListener {
            binding.mineSweeperView.resetGame()
        }

        val status = "minesweeping in progress..."

        Snackbar.make(binding.root, status, Snackbar.LENGTH_LONG)
//                .setAction("Ok") {
//                    binding.tvData.text = "Start over?"
//                }
            .show()

    }

    public fun isFlagModeOn(): Boolean {
        print(binding.toggleFlag.isChecked)
        return binding.toggleFlag.isChecked
    }

}