package hu.ait.travellog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import hu.ait.travellog.CreatePostActivity
import hu.ait.travellog.R
import hu.ait.travellog.data.Post
import hu.ait.travellog.databinding.PostRowBinding

class PostsAdapter: RecyclerView.Adapter<PostsAdapter.ViewHolder>{

    var postItems = mutableListOf<Post>(
        Post("2018. 09. 10", "Entry 1", "Eat"),
        Post("2018. 09. 11", "Entry 2", "Drink")
    )
    val context : Context
    constructor(context: Context) : super() {
        this.context = context
    }

    override fun getItemCount(): Int {
        return postItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = postItems[position]
        holder.bind(post)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val postBinding = PostRowBinding.inflate(LayoutInflater.from(context),
            parent, false)
        return ViewHolder(postBinding)
    }

    inner class ViewHolder(var binding: PostRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.tvDate.text = post.postDate
            binding.tvTitle.text = post.postTitle
            binding.tvText.text = post.postText
        }
    }
}