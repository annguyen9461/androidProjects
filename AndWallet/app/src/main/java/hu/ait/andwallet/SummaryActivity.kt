package hu.ait.andwallet

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import hu.ait.andwallet.databinding.ActivitySummaryBinding

class SummaryActivity : AppCompatActivity() {
    lateinit var binding: ActivitySummaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        if (intent.hasExtra(MainActivity.KEY_DATA)) {
//            binding.tvIncome.text = intent.getStringExtra(MainActivity.KEY_DATA)
//            binding.tvIncome.append(DataManager.data.get(0))
//        }
        if (intent.hasExtra("KEY_EXPENSE")) {
            binding.tvExpense.text = intent.getStringExtra(MainActivity.KEY_EXPENSE)
        }
        if (intent.hasExtra("KEY_INCOME")) {
            binding.tvIncome.text = intent.getStringExtra(MainActivity.KEY_INCOME)
        }
        if (intent.hasExtra("KEY_BALANCE")) {
            binding.tvBalance.text = intent.getStringExtra(MainActivity.KEY_BALANCE)
        }
    }
}