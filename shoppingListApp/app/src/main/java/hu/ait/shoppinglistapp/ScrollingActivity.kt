package hu.ait.shoppinglistapp

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import hu.ait.shoppinglistapp.adapter.ShoppingItemAdapter
import hu.ait.shoppinglistapp.data.ShoppingItem
import hu.ait.shoppinglistapp.databinding.ActivityScrollingBinding
import hu.ait.shoppinglistapp.dialog.ShoppingDialog

class ScrollingActivity : AppCompatActivity(), ShoppingDialog.ShoppingHandler {

    private lateinit var binding: ActivityScrollingBinding
    private lateinit var adapter: ShoppingItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            adapter.addShopping(ShoppingItem("demo", 10, "fast food", 0, false))
        }

        adapter = ShoppingItemAdapter(this)
        binding.recyclerShoppingItem.adapter = adapter
    }

    override fun shoppingItemCreated(shoppingItem: ShoppingItem) {
        adapter.addShopping(shoppingItem)
    }

}