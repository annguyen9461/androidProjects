package hu.ait.travellog.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.gms.maps.model.Marker
import hu.ait.travellog.MarkerDetails
import hu.ait.travellog.MarkerDetails.Companion.KEY_POST_EDIT
import hu.ait.travellog.data.Post
import hu.ait.travellog.databinding.PostDialogBinding
import java.util.*

class PostDialog: DialogFragment() {
    interface PostHandler {
        fun postCreated(post: Post)
        fun postUpdated(post: Post)
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

    private var isEditMode = false

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = AlertDialog.Builder(requireContext())

        // Are we in edit mode? - Have we received a Todo object to edit?
        if (arguments != null && requireArguments().containsKey(
                MarkerDetails.KEY_POST_EDIT)) {
            isEditMode = true
            dialogBuilder.setTitle("Edit Post")
        } else {
            isEditMode = false
            dialogBuilder.setTitle("New Post")
        }

        dialogBuilder.setTitle("New Post")
        binding = PostDialogBinding.inflate(requireActivity().layoutInflater)
        dialogBuilder.setView(binding.root)

        // pre-fill the dialog if we are in edit mode
        if (isEditMode) {
            val todoToEdit =
                requireArguments().getSerializable(
                    Marker.KEY_POST_EDIT) as Post

            binding.etPostTitle.setText(todoToEdit.postTitle)
            binding.etPostText.setText(todoToEdit.postText)
        }
        
        dialogBuilder.setPositiveButton("Ok") {
                dialog, which ->

            postHandler.postCreated(
                Post(
                    null,
                    binding.etPostTitle.text.toString(),
                    binding.etPostText.text.toString(),
                    Date(System.currentTimeMillis()).toString())
            )
        }
        dialogBuilder.setNegativeButton("Cancel") {
                dialog, which ->
        }

        return dialogBuilder.create()
    }

}