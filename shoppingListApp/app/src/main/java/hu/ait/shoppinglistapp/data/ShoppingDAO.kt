package hu.ait.shoppinglistapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ShoppingDAO {

    @Query("SELECT * FROM shoppingtable")
    fun getAllShoppingItems() : LiveData<List<ShoppingItem>>

    @Insert
    fun insertShoppingItem(shoppingItem: ShoppingItem)

    @Delete
    fun deleteShoppingItem(shoppingItem: ShoppingItem)

}