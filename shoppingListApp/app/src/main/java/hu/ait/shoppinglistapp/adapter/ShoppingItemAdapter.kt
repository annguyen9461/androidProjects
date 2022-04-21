package hu.ait.shoppinglistapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.ait.shoppinglistapp.R
import hu.ait.shoppinglistapp.data.ShoppingItem
import hu.ait.shoppinglistapp.databinding.ShoppingRowBinding

class ShoppingItemAdapter : RecyclerView.Adapter<ShoppingItemAdapter.ViewHolder> {

    var shoppingItems = mutableListOf<ShoppingItem>(
        ShoppingItem("pizza", 10, "fast food", 0, false),
        ShoppingItem("broccoli", 10, "healthy food", 10, true)
    )
    val context: Context

    constructor(context: Context) : super() {
        this.context = context
    }

    override fun getItemCount(): Int {
        return shoppingItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val shoppingBinding = ShoppingRowBinding.inflate(
            LayoutInflater.from(context),
            parent, false
        )
        return ViewHolder(shoppingBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shopping = shoppingItems[position]
        holder.bind(shopping)
    }

    inner class ViewHolder(var binding: ShoppingRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(shopping: ShoppingItem) {
            binding.tvName.text = shopping.name
            binding.tvPrice.text = shopping.price.toString()
            binding.tvDescription.text = shopping.description
            binding.tvCategory.text = shopping.category.toString()
            binding.cbBought.isChecked = shopping.isBought
        }
    }

}
