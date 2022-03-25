package hu.ait.andwallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ait.andwallet.databinding.ActivityMainBinding
import hu.ait.andwallet.databinding.MoneyRowBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener() {
            try {
                if (binding.etMoneyAmount.text.isNotEmpty()) {
                    addNewMoneyItem()
                }
                else {
                    binding.etMoneyAmount.error = "This field can not be empty!"
                }
            } catch (e: Exception) {
                binding.etMoneyAmount.error = "Error: ${e.message}"
            }
        }

    }

    private fun addNewMoneyItem() {
        val moneyRow = MoneyRowBinding.inflate(layoutInflater)

        moneyRow.btnDelete.setOnClickListener {
            binding.layoutContent.removeView(moneyRow.root)
        }

        if (binding.cbIsExpense.isChecked) {
            moneyRow.ivIcon.setImageResource(R.drawable.expense)
            binding.etMoneyName.setText("Expense   ")
        } else {
            moneyRow.ivIcon.setImageResource(R.drawable.income)
            binding.etMoneyName.setText("Income   ")
        }

        moneyRow.tvMoneyTitle.text = binding.etMoneyName.text.toString()
        moneyRow.tvMoneyAmt.text = binding.etMoneyAmount.text.toString()

        binding.layoutContent.addView(moneyRow.root)
    }
}