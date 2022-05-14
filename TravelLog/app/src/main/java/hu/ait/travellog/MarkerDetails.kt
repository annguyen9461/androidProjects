package hu.ait.travellog

import android.content.Intent
import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import hu.ait.travellog.adapter.PostsAdapter
import hu.ait.travellog.databinding.ActivityMarkerDetailsBinding

class MarkerDetails : AppCompatActivity() {

    private lateinit var binding: ActivityMarkerDetailsBinding

    private lateinit var postsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMarkerDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            startActivity(Intent(this, CreatePostActivity::class.java))
        }

        postsAdapter = PostsAdapter(this,
            FirebaseAuth.getInstance().currentUser!!.uid)
        binding.recyclerPosts.adapter = postsAdapter
    }
}