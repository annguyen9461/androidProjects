package hu.ait.travellog

import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.core.View
import hu.ait.travellog.data.Post
import hu.ait.travellog.databinding.ActivityCreatePostBinding
import kotlin.concurrent.thread

class CreatePostActivity : AppCompatActivity() {

    companion object {
        const val POSTS_COLLECTION = "posts"
    }

    lateinit var binding: ActivityCreatePostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener {
//            thread {
//                val newPost = Post(null,
//                    binding.etTitle.text.toString(),
//                    binding.etBody.text.toString(),
//                )
//                AppDatabase.getInstance(this).postDAO().insertPost(newPost)
//            }
        }
    }

}