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

        adapter = PostsAdapter(this)
        binding.recyclerPosts.adapter = adapter
    }

    override fun postCreated(post: Post) {
       thread {
           AppDatabase.getInstance(this).postDAO().insertPost(post)

           runOnUiThread {
               adapter.addPost(post)

               Snackbar.make(binding.root, "Post created",Snackbar.LENGTH_LONG)
                   .setAction("Undo") {
                       adapter.deleteLastItem()
                   }
                   .show()
           }
       }
    }


}