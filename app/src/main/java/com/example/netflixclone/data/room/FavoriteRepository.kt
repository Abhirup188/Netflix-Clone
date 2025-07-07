package com.example.netflixclone.data.room

import kotlinx.coroutines.flow.Flow

class FavoriteRepository(private val dao: FavoriteDao) {
    val favorites: Flow<List<FavoriteMovieEntity>> = dao.getAllFavorites()

    suspend fun addFavorite(movie: FavoriteMovieEntity) = dao.insertFavorite(movie)

    suspend fun removeFavorite(movie: FavoriteMovieEntity) = dao.deleteFavorite(movie)

    suspend fun isFavorite(id: Int): Boolean = dao.isFavorite(id)
}
