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

    var shoppingItems = mutableListOf<ShoppingItem>()
    val context: Context

    constructor(context: Context, shoppingList: List<ShoppingItem>) : super() {
        this.context = context

        shoppingItems.addAll(shoppingList)
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

    fun addShopping(newShoppingItem: ShoppingItem) {
        shoppingItems.add(newShoppingItem)
        notifyItemInserted(shoppingItems.lastIndex)
    }

    fun deleteItem(idx: Int) {
        shoppingItems.removeAt(idx)
        notifyItemRemoved(idx)
    }

    fun deleteLastItem() {
        shoppingItems.removeLast()
        notifyItemRemoved(shoppingItems.lastIndex+1)
    }

    inner class ViewHolder(var binding: ShoppingRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(shopping: ShoppingItem) {
            binding.tvName.text = shopping.name
            binding.tvPrice.text = shopping.price
            binding.tvDescription.text = shopping.description
            binding.tvCategory.text = shopping.category
            binding.cbBought.isChecked = shopping.isBought

            binding.btnDelete.setOnClickListener {
                deleteItem(this.adapterPosition)
            }
        }
    }

}
