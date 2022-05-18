package hu.ait.wanderlog.data

import androidx.room.*

@Dao
interface PostDAO {
    @Query("SELECT * FROM post")
    fun getAllPosts(): List<Post>

    @Insert
    fun insertPost(post: Post): Long

    @Delete
    fun deletePost(post: Post)

    @Update
    fun updatePost(post: Post)
}