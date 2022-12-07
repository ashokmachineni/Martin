package com.example.martin.domain

import com.example.martin.data.MovieData

interface MoviesRepository {
    suspend fun getAllMovies(): MovieData
}