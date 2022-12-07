package com.example.martin.domain

import com.example.martin.data.Result
import com.example.martin.data.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    operator fun invoke(): Flow<Resource<List<Result>>> = flow {

        try {
            emit(Resource.Loading())
            val moviesData = moviesRepository.getAllMovies().results
            emit(Resource.Success(moviesData))

        } catch (e: IOException){
            emit(Resource.Error(e.localizedMessage))
        }catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage))
        }
    }
}