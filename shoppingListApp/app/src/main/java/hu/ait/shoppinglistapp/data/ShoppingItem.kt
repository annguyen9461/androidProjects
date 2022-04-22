package hu.ait.shoppinglistapp.data

data class ShoppingItem(
    var name: String,
    var price: String,
    var description: String,
    var category: String,
    var isBought: Boolean
)