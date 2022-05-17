package hu.ait.travellog.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostDAO {
    @Query("SELECT * FROM post")
    fun getAllGrades(): List<Post>

    @Insert
    fun insertPost(post: Post): Long

    @Delete
    fun deletePost(post: Post)

}