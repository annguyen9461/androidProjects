package hu.ait.weatherinfo.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import hu.ait.weatherinfo.data.City
import hu.ait.weatherinfo.databinding.CityDialogBinding

class CityDialog : DialogFragment(){
    interface CityHandler {
        fun cityCreated(city: City)
    }

    lateinit var cityHandler: CityHandler

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is CityHandler){
            cityHandler = context
        } else {
            throw RuntimeException(
                "The Activity is not implementing the CityHandler interface.")
        }
    }

    lateinit var etCityNameText: EditText
    lateinit var binding: CityDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = AlertDialog.Builder(requireContext())

        dialogBuilder.setTitle("City name")
        binding = CityDialogBinding.inflate(requireActivity().layoutInflater)
        dialogBuilder.setView(binding.root)

        dialogBuilder.setPositiveButton("Ok") {
                dialog, which ->

            cityHandler.cityCreated(
                City(
                    binding.etCityName.text.toString())
            )
        }
        dialogBuilder.setNegativeButton("Cancel") {
                dialog, which ->
        }

        return dialogBuilder.create()
    }
}