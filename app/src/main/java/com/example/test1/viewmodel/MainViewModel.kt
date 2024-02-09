package com.example.test1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test1.network.request.Async
import com.example.test1.ui.viewholder.uimodel.BaseDataModel
import com.example.test1.usecase.MovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


data class MainUiState(
    val items: List<BaseDataModel> = emptyList(),
    val isLoading: Boolean = false,
    val userMessage: Int? = null,
    val initial: Boolean = false
)

class MainViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {

    private val _userMessage = MutableStateFlow(null)
    private val _isLoading = MutableStateFlow(false)
    private val _items =  movieUseCase.movies

    val uiState: StateFlow<MainUiState> = combine(
         _isLoading, _userMessage, _items
    ) { loading, message, items ->
        when(items){
            Async.Loading -> {
                MainUiState(isLoading = true)
            }

            is Async.Error -> {
                MainUiState(userMessage = items.errorCode)
            }

            is Async.Success -> {
                MainUiState(
                    items = items.data,
                    isLoading = loading,
                    userMessage = message
                )
            }

            is Async.None -> {
                MainUiState(initial = true)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000),
        initialValue = MainUiState(isLoading = true)
    )

    suspend fun getMovies(page: Int){
        movieUseCase.getMovieStream(page)
    }
}