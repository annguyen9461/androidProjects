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
        holder.tvDate.text = post.postDate
        holder.tvPostTitle.text = post.postTitle
        holder.tvPostText.text = post.postText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.post_row, parent, false
        )
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val tvPostTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvPostText: TextView = itemView.findViewById(R.id.tvText)
    }
}