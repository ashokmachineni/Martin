package com.example.martin.domain

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.martin.data.Resource
import com.example.martin.data.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase): ViewModel() {

    private val _movieState = mutableStateOf(MoviesState())
    val movieState : State<MoviesState> = _movieState

    init {
        getMoviesData()
    }
    private fun getMoviesData(){
        moviesUseCase().onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _movieState.value = MoviesState(true)
                }
                is Resource.Error -> {
                    _movieState.value =MoviesState(isError = "Something wrong")
                }
                is Resource.Success -> {
                    _movieState.value =MoviesState(isSuccess = result.data ?: emptyList())
                }

            }
        }.launchIn(viewModelScope)
    }
}

data class MoviesState(
    val isLoading: Boolean = false,
    val isSuccess: List<Result> = emptyList(),
    val isError: String = ""
)