package hu.ait.shoppinglistapp.data

data class ShoppingItem(
    var name: String,
    var price: Int,
    var description: String,
    var category: Int,
    var isBought: Boolean
)