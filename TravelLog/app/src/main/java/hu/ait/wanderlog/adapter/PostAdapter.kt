package hu.ait.wanderlog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.ait.wanderlog.MarkerDetails
import hu.ait.wanderlog.data.AppDatabase
import hu.ait.wanderlog.data.Post
import hu.ait.wanderlog.databinding.PostRowBinding
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

        }
    }
}