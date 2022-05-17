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
import hu.ait.travellog.data.Post
import hu.ait.travellog.databinding.ActivityMarkerDetailsBinding

class MarkerDetails : AppCompatActivity() {

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
            adapter.addPost(Post("17/5/2022","Title 1", "Some text"))
        }

        adapter = PostsAdapter(this)
        binding.recyclerPosts.adapter = adapter
    }


}