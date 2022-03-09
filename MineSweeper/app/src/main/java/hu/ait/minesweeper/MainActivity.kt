package hu.ait.minesweeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

        binding.toggleFlag.setOnClickListener {
            if (isFlagModeOn() ) {
                binding.mineSweeperView.changeFlagOn()
            } else {
                binding.mineSweeperView.changeFlagOff()
            }
        }
    }

//    public fun showText(message: String) {
//        //Toast.makeText(this, message, Toast.LENGTH_LONG).show()
//        binding.tvMessage.text = message
//    }

    public fun isFlagModeOn() : Boolean {
        return binding.toggleFlag.isChecked
    }

}