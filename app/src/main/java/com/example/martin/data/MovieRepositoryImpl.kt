package com.example.martin.data

import com.example.martin.domain.MoviesRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor (private val api: MovieApi): MoviesRepository {
    override suspend fun getAllMovies(): MovieData {
        return api.getAllMovies("7f945f914277633f019081c00561f647")
    }
}