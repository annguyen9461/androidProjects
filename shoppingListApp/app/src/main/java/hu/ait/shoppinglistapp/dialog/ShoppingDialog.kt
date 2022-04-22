package hu.ait.shoppinglistapp.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import hu.ait.shoppinglistapp.data.ShoppingItem
import hu.ait.shoppinglistapp.databinding.ShoppingDialogBinding

class ShoppingDialog : DialogFragment() {

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

    lateinit var binding: ShoppingDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = AlertDialog.Builder(requireContext())

        dialogBuilder.setTitle("Shopping dialog")
        binding = ShoppingDialogBinding.inflate(requireActivity().layoutInflater)
        dialogBuilder.setView(binding.root)

        dialogBuilder.setPositiveButton("Ok") {
                dialog, which ->

            shoppingHandler.shoppingItemCreated(
                ShoppingItem(
                    binding.etShoppingItemName.text.toString(),
                    binding.etShoppingItemPrice.text.toString(),
                    binding.etShoppingItemDescription.text.toString(),
                    binding.etShoppingItemCategory.text.toString(),
                    binding.cbShoppingItemBought.isChecked
                )
            )
        }
        dialogBuilder.setNegativeButton("Cancel") {
                dialog, which ->
        }

        return dialogBuilder.create()
    }

}