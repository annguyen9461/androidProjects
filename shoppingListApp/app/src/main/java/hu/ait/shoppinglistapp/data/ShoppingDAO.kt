package hu.ait.shoppinglistapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingDAO {

    @Query("SELECT * FROM shoppingtable")
    fun getAllShoppingItems() : LiveData<List<ShoppingItem>>

    @Insert
    fun insertShoppingItem(shoppingItem: ShoppingItem)

    @Delete
    fun deleteShoppingItem(shoppingItem: ShoppingItem)

    @Query("DELETE FROM shoppingtable")
    fun deleteAll()

    @Update
    fun updateShopping(shoppingItem: ShoppingItem)

}