package hu.ait.travellog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import hu.ait.travellog.CreatePostActivity
import hu.ait.travellog.MarkerDetails
import hu.ait.travellog.R
import hu.ait.travellog.data.AppDatabase
import hu.ait.travellog.data.Post
import hu.ait.travellog.databinding.PostRowBinding
import kotlin.concurrent.thread

class PostsAdapter: RecyclerView.Adapter<PostsAdapter.ViewHolder>{

    var postItems = mutableListOf<Post>()
    val context : Context
    constructor(context: Context, postList: List<Post>) : super() {
        this.context = context

        postItems.addAll(postList)
    }

    override fun getItemCount(): Int {
        return postItems.size
    }

    fun addPost(newPost: Post) {
        postItems.add(newPost)
        notifyItemInserted(postItems.lastIndex) // refreshes the recyclerview only where the new item was added
        //notifyDataSetChanged() - redraws the whole recyclerView
    }

    fun deleteItem(idx: Int) {
        thread{
            AppDatabase.getInstance(context).postDAO().deletePost(postItems[idx])

            (context as MarkerDetails).runOnUiThread {
                postItems.removeAt(idx)
                notifyItemRemoved(idx)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val postBinding = PostRowBinding.inflate(LayoutInflater.from(context),
            parent, false)
        return ViewHolder(postBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = postItems[position]
        holder.bind(post)
    }

    inner class ViewHolder(var binding: PostRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.tvTitle.text = post.postTitle
            binding.tvText.text = post.postText
            binding.tvDate.text = post.postDate

            binding.btnDelete.setOnClickListener {
                deleteItem(this.adapterPosition)
            }

            binding.btnEdit.setOnClickListener {
                (context as MarkerDetails).showEditDialog(
                    getItem(this.adapterPosition)
                )
            }
        }
    }
}