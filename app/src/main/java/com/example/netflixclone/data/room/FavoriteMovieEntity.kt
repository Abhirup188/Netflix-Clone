package com.example.netflixclone.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteMovieEntity(
    @PrimaryKey(autoGenerate = true)
    val movieId:Int,
    val title:String,
    val posterPath:String?,
    val overview:String
)
