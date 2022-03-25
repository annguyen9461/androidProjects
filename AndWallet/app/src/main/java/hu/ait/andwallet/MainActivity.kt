package hu.ait.andwallet

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import hu.ait.andwallet.databinding.ActivityMainBinding
import hu.ait.andwallet.databinding.MoneyRowBinding

class MainActivity : AppCompatActivity() {

    companion object {
        var KEY_EXPENSE = "KEY_EXPENSE"
        var KEY_INCOME = "KEY_INCOME"
        var KEY_BALANCE = "KEY_BALANCE"
    }

    lateinit var binding: ActivityMainBinding
    var totalIncome = 0
    var totalExpense = 0
    var balance = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener() {
            try {
                if (binding.etMoneyAmount.text.isNotEmpty()) {
                    addNewMoneyItem()
                } else {
                    binding.etMoneyAmount.error = "Amount of money field can not be empty!"
                }
            } catch (e: Exception) {
                binding.etMoneyAmount.error = "Error: ${e.message}"
            }
        }

        binding.btnSummary.setOnClickListener {
            val intentSummary = Intent()
            intentSummary.setClass(
                this,
                SummaryActivity::class.java
            )

            intentSummary.putExtra("KEY_EXPENSE",
                totalExpense.toString())

            intentSummary.putExtra("KEY_INCOME",
                totalIncome.toString())

            intentSummary.putExtra("KEY_BALANCE",
                balance.toString())

            startActivity(intentSummary)
        }

    }

    private fun addNewMoneyItem() {
        val moneyRow = MoneyRowBinding.inflate(layoutInflater)

        moneyRow.btnDelete.setOnClickListener {
            binding.layoutContent.removeView(moneyRow.root)
            if (moneyRow.tvMoneyTitle.text == "Expense   ") {
                totalExpense = totalExpense - moneyRow.tvMoneyAmt.length()
            } else {
                totalIncome = totalIncome - moneyRow.tvMoneyAmt.length()
            }
            balance = totalIncome - totalExpense
        }

        if (binding.cbIsExpense.isChecked) {
            moneyRow.ivIcon.setImageResource(R.drawable.expense)
            binding.etMoneyName.setText(getString(R.string.expense_space))
            totalExpense = totalExpense + binding.etMoneyAmount.length()
        } else {
            moneyRow.ivIcon.setImageResource(R.drawable.income)
            binding.etMoneyName.setText(getString(R.string.income_space))
            totalIncome = totalIncome + binding.etMoneyAmount.length()
        }

        balance = totalIncome - totalExpense

        moneyRow.tvMoneyTitle.text = binding.etMoneyName.text.toString()
        moneyRow.tvMoneyAmt.text = binding.etMoneyAmount.text.toString()

        binding.layoutContent.addView(moneyRow.root)
    }
}