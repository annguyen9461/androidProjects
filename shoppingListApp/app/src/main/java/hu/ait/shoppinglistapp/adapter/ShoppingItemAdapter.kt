package hu.ait.shoppinglistapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.ait.shoppinglistapp.R
import hu.ait.shoppinglistapp.ScrollingActivity
import hu.ait.shoppinglistapp.data.AppDatabase
import hu.ait.shoppinglistapp.data.ShoppingItem
import hu.ait.shoppinglistapp.databinding.ShoppingRowBinding
import kotlin.concurrent.thread

class ShoppingItemAdapter(var context: Context) :
    ListAdapter<ShoppingItem, ShoppingItemAdapter.ViewHolder>(
        ShoppingDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val shoppingBinding = ShoppingRowBinding.inflate(
            LayoutInflater.from(context),
            parent, false
        )
        return ViewHolder(shoppingBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shopping = getItem(position)
        holder.bind(shopping)
    }

    fun deleteItem(idx: Int) {
        thread {
            AppDatabase.getInstance(context).shoppingDao().deleteShoppingItem(getItem(idx))
        }
    }

    fun deleteLastItem() {
//        shoppingItems.removeLast()
//        notifyItemRemoved(shoppingItems.lastIndex+1)
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

            binding.btnEdit.setOnClickListener {
                (context as ScrollingActivity).showEditDialog(
                    getItem(this.adapterPosition)
                )
            }

            binding.btnDetails.setOnClickListener {
                (context as ScrollingActivity).showDetailsDialog(
                    getItem(this.adapterPosition)
                )
            }

            binding.cbBought.setOnClickListener {
                val currentItem = getItem(adapterPosition)
                currentItem.isBought = binding.cbBought.isChecked

                thread {
                    AppDatabase.getInstance(context).shoppingDao().updateShopping(currentItem)
                }
            }

            if (binding.tvCategory.text.toString() == "Food") {
                binding.ivCategoryIcon.setImageResource(R.drawable.food)
            } else if (binding.tvCategory.text.toString() == "Beverage"){
                binding.ivCategoryIcon.setImageResource(R.drawable.drink)
            } else {
                binding.ivCategoryIcon.setImageResource(R.drawable.clothing)
            }
        }
    }

}

class ShoppingDiffCallback : DiffUtil.ItemCallback<ShoppingItem>() {
    override fun areItemsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
        return oldItem.shoppingid == newItem.shoppingid
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
        return oldItem == newItem
    }
}