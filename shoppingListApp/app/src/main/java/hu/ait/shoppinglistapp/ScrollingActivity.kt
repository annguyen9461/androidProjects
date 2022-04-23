package hu.ait.shoppinglistapp

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import hu.ait.shoppinglistapp.adapter.ShoppingItemAdapter
import hu.ait.shoppinglistapp.data.AppDatabase
import hu.ait.shoppinglistapp.data.ShoppingItem
import hu.ait.shoppinglistapp.databinding.ActivityScrollingBinding
import hu.ait.shoppinglistapp.dialog.DetailsDialog
import hu.ait.shoppinglistapp.dialog.ShoppingDialog
import kotlin.concurrent.thread

class ScrollingActivity : AppCompatActivity(), ShoppingDialog.ShoppingHandler {

    private lateinit var binding: ActivityScrollingBinding
    private lateinit var adapter: ShoppingItemAdapter

    companion object {
        const val KEY_SHOPPING_EDIT = "KEY_SHOPPING_EDIT"
        const val KEY_DETAILS = "KEY_DETAILS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            ShoppingDialog().show(supportFragmentManager, "SHOPPING_DIALOG")
        }
        binding.btnDeleteAll.setOnClickListener {
            thread {
                AppDatabase.getInstance(this@ScrollingActivity).shoppingDao().deleteAll()
            }
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {

        adapter = ShoppingItemAdapter(this)
        binding.recyclerShoppingItem.adapter = adapter
        val shoppingItems = AppDatabase.getInstance(this).shoppingDao().getAllShoppingItems()
        shoppingItems.observe(this, Observer { items ->
            adapter.submitList(items)
        })
    }

    fun showEditDialog(shoppingToEdit: ShoppingItem) {
        val dialog = ShoppingDialog()

        val bundle = Bundle()
        bundle.putSerializable(KEY_SHOPPING_EDIT, shoppingToEdit)
        dialog.arguments = bundle

        dialog.show(supportFragmentManager, "TAG_ITEM_EDIT")
    }

    fun showDetailsDialog(detailsToShow: ShoppingItem) {
        val dialog = DetailsDialog()

        val bundle = Bundle()
        bundle.putSerializable(KEY_DETAILS, detailsToShow)
        dialog.arguments = bundle

        dialog.show(supportFragmentManager, "TAG_DETAILS_SHOW")
    }

    override fun shoppingItemCreated(shoppingItem: ShoppingItem) {
        thread {
            AppDatabase.getInstance(this).shoppingDao().insertShoppingItem(shoppingItem)
        }
    }

    override fun updateShopping(shoppingItem: ShoppingItem) {
        thread {
            AppDatabase.getInstance(this).shoppingDao().updateShopping(shoppingItem)
        }
    }

    fun showItemDetails(shoppingItem: ShoppingItem) {
        thread {
            AppDatabase.getInstance(this).shoppingDao().getAllShoppingItems()
        }
    }

}