package hu.ait.shoppinglistapp.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import hu.ait.shoppinglistapp.R
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
                    ScrollingActivity.KEY_SHOPPING_EDIT
                ) as ShoppingItem

            binding.etShoppingItemName.setText(shoppingToEdit.name)
            binding.etShoppingItemPrice.setText(shoppingToEdit.price)
            binding.etShoppingItemDescription.setText(shoppingToEdit.description)
//            binding.spinnerCategories.setText(shoppingToEdit.category)
            binding.cbShoppingItemBought.isChecked = shoppingToEdit.isBought
        }

        dialogBuilder.setPositiveButton("Ok") { dialog, which ->
            if (binding.etShoppingItemName.text.isEmpty()
                || binding.etShoppingItemPrice.text.isEmpty()
            ) {
                binding.etShoppingItemName.error = "This field can not be empty"
                binding.etShoppingItemPrice.error = "This field can not be empty"
                Toast.makeText(context, "To add or edit an item, Name and Price cannot be empty",
                    Toast.LENGTH_LONG).show()
            } else {
                if (isEditMode) {
                    val shoppingToEdit =
                        (requireArguments().getSerializable(
                            ScrollingActivity.KEY_SHOPPING_EDIT
                        ) as ShoppingItem).copy(
                            name = binding.etShoppingItemName.text.toString(),
                            price = binding.etShoppingItemPrice.text.toString(),
                            description = binding.etShoppingItemDescription.text.toString(),
                            category = binding.spinnerCategories.selectedItem.toString(),
                            isBought = binding.cbShoppingItemBought.isChecked
                        )

                    shoppingHandler.updateShopping(shoppingToEdit)
                } else {
                    shoppingHandler.shoppingItemCreated(
                        ShoppingItem(
                            null,
                            binding.etShoppingItemName.text.toString(),
                            binding.etShoppingItemPrice.text.toString(),
                            binding.etShoppingItemDescription.text.toString(),
                            binding.spinnerCategories.selectedItem.toString(),
                            binding.cbShoppingItemBought.isChecked
                        )
                    )
                }
            }

        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, which ->
        }

        val categoriesAdapter = context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.categories_array,
                android.R.layout.simple_spinner_item
            )
        }
        if (categoriesAdapter != null) {
            categoriesAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
            )
        }
        binding.spinnerCategories.adapter = categoriesAdapter

        binding.spinnerCategories.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    Toast.makeText(
                        context,
                        binding.spinnerCategories.selectedItem.toString(), Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }

        return dialogBuilder.create()
    }

}