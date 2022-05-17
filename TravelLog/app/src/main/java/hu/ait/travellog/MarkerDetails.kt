package hu.ait.travellog

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import hu.ait.travellog.adapter.PostsAdapter
import hu.ait.travellog.data.AppDatabase
import hu.ait.travellog.data.Post
import hu.ait.travellog.databinding.ActivityMarkerDetailsBinding
import hu.ait.travellog.dialog.PostDialog
import kotlin.concurrent.thread

class MarkerDetails : AppCompatActivity(), PostDialog.PostHandler {

    companion object {
        const val KEY_POST_EDIT = "KEY_POST_EDIT"
    }

    private lateinit var binding: ActivityMarkerDetailsBinding
    private lateinit var adapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMarkerDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title

        binding.fab.setOnClickListener {
            PostDialog().show(supportFragmentManager,"POST_DIALOG")
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        thread {
            val postItems = AppDatabase.getInstance(this).postDAO().getAllPosts()

            runOnUiThread {
                adapter = PostsAdapter(this, postItems)
                binding.recyclerPosts.adapter = adapter
            }
        }
    }

    override fun postCreated(post: Post) {
       thread {
           AppDatabase.getInstance(this).postDAO().insertPost(post)

           runOnUiThread {
               adapter.addPost(post)

               Snackbar.make(binding.root, "Post created",Snackbar.LENGTH_LONG)
           }
       }
    }

    fun showEditDialog(postToEdit: Post) {
        val dialog = PostDialog()

        val bundle = Bundle()
        bundle.putSerializable("KEY_POST_EDIT", postToEdit)
        dialog.arguments = bundle

        dialog.show(supportFragmentManager, "TAG_ITEM_EDIT")
    }

    override fun postUpdated(post: Post) {
        thread {
            AppDatabase.getInstance(this).postDAO().updatePost(post)
        }
    }

}