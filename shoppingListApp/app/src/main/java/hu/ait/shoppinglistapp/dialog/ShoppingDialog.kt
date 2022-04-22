package hu.ait.shoppinglistapp.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import hu.ait.shoppinglistapp.ScrollingActivity
import hu.ait.shoppinglistapp.data.ShoppingItem
import hu.ait.shoppinglistapp.databinding.ShoppingDialogBinding

class ShoppingDialog : DialogFragment() {

    interface ShoppingHandler {
        fun shoppingItemCreated(shoppingItem: ShoppingItem)

        fun updateShopping(shoppingItem: ShoppingItem)
    }

    lateinit var shoppingHandler: ShoppingHandler

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is ShoppingHandler) {
            shoppingHandler = context
        } else {
            throw RuntimeException(
                "The Activity is not implementing the TodoHandler interface."
            )
        }
    }

    lateinit var binding: ShoppingDialogBinding

    private var isEditMode = false

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = AlertDialog.Builder(requireContext())

        // Are we in edit mode? - Have we received a ShoppingItem object to edit?
        if (arguments != null && requireArguments().containsKey(
                ScrollingActivity.KEY_SHOPPING_EDIT
            )
        ) {
            isEditMode = true
            dialogBuilder.setTitle("Edit Todo")
        } else {
            isEditMode = false
            dialogBuilder.setTitle("New Todo")
        }

        binding = ShoppingDialogBinding.inflate(requireActivity().layoutInflater)
        dialogBuilder.setView(binding.root)

        // pre-fill the dialog if we are in edit mode
        if (isEditMode) {
            val shoppingToEdit =
                requireArguments().getSerializable(
                    ScrollingActivity.KEY_SHOPPING_EDIT) as ShoppingItem

            binding.etShoppingItemName.setText(shoppingToEdit.name)
            binding.etShoppingItemPrice.setText(shoppingToEdit.price)
            binding.etShoppingItemDescription.setText(shoppingToEdit.description)
            binding.etShoppingItemCategory.setText(shoppingToEdit.category)
            binding.cbShoppingItemBought.isChecked = shoppingToEdit.isBought
        }

        dialogBuilder.setPositiveButton("Ok") { dialog, which ->
            if (isEditMode) {
                val shoppingToEdit =
                    requireArguments().getSerializable(
                        ScrollingActivity.KEY_SHOPPING_EDIT) as ShoppingItem

                shoppingToEdit.name = binding.etShoppingItemName.text.toString()
                shoppingToEdit.price = binding.etShoppingItemPrice.text.toString()
                shoppingToEdit.description = binding.etShoppingItemDescription.text.toString()
                shoppingToEdit.category = binding.etShoppingItemCategory.text.toString()
                shoppingToEdit.isBought = binding.cbShoppingItemBought.isChecked

                shoppingHandler.updateShopping(shoppingToEdit)
            } else {
                shoppingHandler.shoppingItemCreated(
                    ShoppingItem(
                        null,
                        binding.etShoppingItemName.text.toString(),
                        binding.etShoppingItemPrice.text.toString(),
                        binding.etShoppingItemDescription.text.toString(),
                        binding.etShoppingItemCategory.text.toString(),
                        binding.cbShoppingItemBought.isChecked
                    )
                )
            }
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, which ->
        }

        return dialogBuilder.create()
    }

}