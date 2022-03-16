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
            binding.tvData.text = "minesweeping in progress..."
        }

        val status = "minesweeping in progress..."
        binding.tvData.text = status

        Snackbar.make(binding.root, status, Snackbar.LENGTH_LONG)
//                .setAction("Ok") {
//                    binding.tvData.text = "Start over?"
//                }
            .show()

    }

//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//    fun revealCard() {
//        val x = binding.cardView.getRight()
//        val y = binding.cardView.getBottom()
//
//        val startRadius = 0
//        val endRadius = Math.hypot(binding.cardView.getWidth().toDouble(),
//            binding.cardView.getHeight().toDouble()).toInt()
//
//        val anim = ViewAnimationUtils.createCircularReveal(
//            binding.cardView,
//            x,
//            y,
//            startRadius.toFloat(),
//            endRadius.toFloat()
//        )
//
//        binding.cardView.setVisibility(View.VISIBLE)
//        anim.start()
//    }

//    public fun showText(message: String) {
//        //Toast.makeText(this, message, Toast.LENGTH_LONG).show()
//        binding.tvMessage.text = message
//    }

    public fun isFlagModeOn(): Boolean {
        print(binding.toggleFlag.isChecked)
        return binding.toggleFlag.isChecked
    }

}