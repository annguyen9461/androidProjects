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
import hu.ait.shoppinglistapp.databinding.DetailsDialogBinding
import hu.ait.shoppinglistapp.databinding.ShoppingDialogBinding

class DetailsDialog : DialogFragment() {

    interface DetailsHandler {
        fun showItemDetails(shoppingItem: ShoppingItem)
    }

    lateinit var detailsHandler: DetailsHandler

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is DetailsHandler) {
            detailsHandler = context
        } else {
            throw RuntimeException(
                "The Activity is not implementing the TodoHandler interface."
            )
        }
    }

    lateinit var binding: DetailsDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setTitle("Item Details")
        binding = DetailsDialogBinding.inflate(requireActivity().layoutInflater)
        dialogBuilder.setView(binding.root)

        dialogBuilder.setPositiveButton("Ok") { dialog, which ->
            detailsHandler.showItemDetails(
                ShoppingItem(
                    null,
                    binding.tvName.text.toString(),
                    binding.tvPrice.text.toString(),
                    binding.tvDescription.text.toString(),
                    binding.tvCategory.toString(),
                    binding.cbBought.isChecked
                )
            )
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, which ->
        }

        return dialogBuilder.create()
    }

}