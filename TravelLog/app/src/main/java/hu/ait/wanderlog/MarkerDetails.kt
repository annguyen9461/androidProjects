package hu.ait.wanderlog

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.*
import hu.ait.wanderlog.adapter.PostsAdapter
import hu.ait.wanderlog.data.AppDatabase
import hu.ait.wanderlog.data.Post
import hu.ait.wanderlog.databinding.ActivityMarkerDetailsBinding
import hu.ait.wanderlog.dialog.PostDialog
import kotlin.concurrent.thread

class MarkerDetails : AppCompatActivity(), PostDialog.PostHandler {

    private lateinit var binding: ActivityMarkerDetailsBinding
    private lateinit var adapter: PostsAdapter
    private var listenerReg: ListenerRegistration? = null

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


}