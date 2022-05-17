package hu.ait.travellog.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import hu.ait.travellog.data.Post
import hu.ait.travellog.databinding.PostDialogBinding
import java.util.*

class PostDialog: DialogFragment() {
    interface PostHandler {
        fun postCreated(post: Post)
    }

    lateinit var postHandler: PostHandler

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is PostHandler){
            postHandler = context
        } else {
            throw RuntimeException(
                "The Activity is not implementing the PostHandler interface.")
        }
    }

    lateinit var binding: PostDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = AlertDialog.Builder(requireContext())

        dialogBuilder.setTitle("New Post")
        binding = PostDialogBinding.inflate(requireActivity().layoutInflater)
        dialogBuilder.setView(binding.root)

        dialogBuilder.setPositiveButton("Ok") {
                dialog, which ->

            postHandler.postCreated(
                Post(
                    Date(System.currentTimeMillis()).toString(),
                    binding.etPostTitle.text.toString(),
                    binding.etPostText.text.toString())
            )
        }
        dialogBuilder.setNegativeButton("Cancel") {
                dialog, which ->
        }

        return dialogBuilder.create()
    }

}