package hu.ait.wanderlog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ait.wanderlog.databinding.ActivityCreatePostBinding

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