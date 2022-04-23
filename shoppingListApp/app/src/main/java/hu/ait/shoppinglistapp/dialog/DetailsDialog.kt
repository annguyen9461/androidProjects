package hu.ait.shoppinglistapp.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import hu.ait.shoppinglistapp.R
import hu.ait.shoppinglistapp.ScrollingActivity
import hu.ait.shoppinglistapp.data.ShoppingItem
import hu.ait.shoppinglistapp.databinding.DetailsDialogBinding
import hu.ait.shoppinglistapp.databinding.ShoppingDialogBinding

class DetailsDialog : DialogFragment() {
    lateinit var binding: DetailsDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setTitle("Item Details")

        binding = DetailsDialogBinding.inflate(requireActivity().layoutInflater)
        dialogBuilder.setView(binding.root)

        val detailsToShow =
            requireArguments().getSerializable(
                ScrollingActivity.KEY_DETAILS
            ) as ShoppingItem

        dialogBuilder.setPositiveButton("Ok") { dialog, which ->
            binding.tvName.setText(detailsToShow.name)
            binding.tvPrice.setText(detailsToShow.price)
            binding.tvCategory.setText(detailsToShow.category)
            binding.tvDescription.setText(detailsToShow.description)
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, which ->
        }

        return dialogBuilder.create()
    }

}