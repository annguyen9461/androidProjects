package hu.ait.shoppinglistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import hu.ait.shoppinglistapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        if (intent.hasExtra(ScrollingActivity.KEY_DATA)) {
//            binding.tvData.text = intent.getStringExtra(ScrollingActivity.KEY_DATA)
//            binding.tvData.append(DataManager.data.get(0))
//        }

        val categoriesAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.categories_array,
            android.R.layout.simple_spinner_item
        )
        categoriesAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategories.adapter = categoriesAdapter

        binding.spinnerCategories.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(this@DetailsActivity,
                    binding.spinnerCategories.selectedItem.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }
}