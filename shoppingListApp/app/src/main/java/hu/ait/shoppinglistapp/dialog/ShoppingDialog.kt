package hu.ait.shoppinglistapp.dialog

import android.content.Context
import androidx.fragment.app.DialogFragment
import hu.ait.shoppinglistapp.data.ShoppingItem

class ShoppingDialog : DialogFragment {

    interface ShoppingHandler {
        fun shoppingItemCreated(shoppingItem: ShoppingItem)
    }

    lateinit var shoppingHandler: ShoppingHandler

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is ShoppingHandler){
            shoppingHandler = context
        } else {
            throw RuntimeException(
                "The Activity is not implementing the TodoHandler interface.")
        }
    }

}