package hu.ait.travellog.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostDAO {
    @Query("SELECT * FROM post")
    fun getAllGrades(): List<Post>

    @Query("SELECT * FROM post WHERE post = :post")
    fun getSpecificPosts(grade: String): List<Post>

    @Insert
    fun insertPost(post: Post): Long

    @Delete
    fun deletePost(post: Post)

}