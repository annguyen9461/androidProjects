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

class ShoppingItemAdapter : RecyclerView.Adapter<ShoppingItemAdapter.ViewHolder>{

    var shoppingItems = mutableListOf<ShoppingItem>(
        ShoppingItem("pizza", 10, "fast food", 0, false),
        ShoppingItem("broccoli", 10, "healthy food", 10, true)
    )
    val context : Context
    constructor(context: Context) : super() {
        this.context = context
    }

    override fun getItemCount(): Int {
        return shoppingItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.shopping_row, parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shopping = shoppingItems[position]

        holder.tvName.text = shopping.name
        holder.tvPrice.text = shopping.price.toString()
        holder.tvDescription.text = shopping.description
        holder.tvCategory.text = shopping.category.toString()
        holder.cbBought.isChecked = shopping.bought
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
        val cbBought: CheckBox = itemView.findViewById(R.id.cbBought)
    }

}
