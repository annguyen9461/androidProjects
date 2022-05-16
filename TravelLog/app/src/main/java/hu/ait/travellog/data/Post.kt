package hu.ait.travellog.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class Post(@PrimaryKey(autoGenerate = true) var postId: Long?,
                 @ColumnInfo(name = "studentid") var studentId: String,
                 @ColumnInfo(name = "post") var post: String,
                 @ColumnInfo(name = "datePosted") var datePosted: String
)