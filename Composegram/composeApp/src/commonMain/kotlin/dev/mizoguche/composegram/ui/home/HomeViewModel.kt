package dev.mizoguche.composegram.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.mizoguche.composegram.domain.post.Post
import dev.mizoguche.composegram.domain.post.PostRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Idle(
        val posts: List<Post>,
    ) : HomeUiState()
    data object Empty : HomeUiState()
    data object Error : HomeUiState()
}

class HomeViewModel(
    private val postRepository: PostRepository,
) : ViewModel() {
    data class ViewModelState(
        val isLoading: Boolean = true,
        val posts: List<Post> = listOf(),
        val errorMessage: String? = null,
    ) {
        fun toUiState(): HomeUiState = when {
            isLoading -> HomeUiState.Loading
            errorMessage != null -> HomeUiState.Error
            posts.isEmpty() -> HomeUiState.Empty
            else -> HomeUiState.Idle(posts)
        }
    }

    private val _state = MutableStateFlow(ViewModelState())
    val uiState: StateFlow<HomeUiState> = _state
        .map { it.toUiState() }
        .stateIn(
            scope = viewModelScope,
            initialValue = HomeUiState.Loading,
            started = SharingStarted.WhileSubscribed(5_000),
        )

    init {
        viewModelScope.launch {
            load()
        }
    }

    suspend fun load() {
        _state.update {  it.copy(isLoading = true, errorMessage = null) }
        postRepository.select()
            .fold(
                ifLeft = { error ->
                    _state.update { it.copy(isLoading = false, errorMessage = error.errorMessage) }
                },
                ifRight = { posts ->
                    _state.update { it.copy(isLoading = false, posts = posts) }
                }
            )
    }
}