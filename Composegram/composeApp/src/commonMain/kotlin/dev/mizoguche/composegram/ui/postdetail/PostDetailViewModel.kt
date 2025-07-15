package dev.mizoguche.composegram.ui.postdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.mizoguche.composegram.domain.post.Post
import dev.mizoguche.composegram.domain.post.PostId
import dev.mizoguche.composegram.domain.post.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class PostDetailUiState {
    data object Loading : PostDetailUiState()

    data class Idle(
        val post: Post,
    ) : PostDetailUiState()

    data object Error : PostDetailUiState()
}

class PostDetailViewModel(
    private val postRepository: PostRepository,
    private val postId: PostId,
) : ViewModel() {
    data class ViewModelState(
        val isLoading: Boolean = true,
        val post: Post? = null,
        val errorMessage: String? = null,
    ) {
        fun toUiState(): PostDetailUiState =
            when {
                isLoading -> PostDetailUiState.Loading
                errorMessage != null -> PostDetailUiState.Error
                post != null -> PostDetailUiState.Idle(post)
                else -> PostDetailUiState.Error
            }
    }

    private val state = MutableStateFlow(ViewModelState())
    val uiState: StateFlow<PostDetailUiState> =
        state
            .map { it.toUiState() }
            .stateIn(
                scope = viewModelScope,
                initialValue = PostDetailUiState.Loading,
                started = SharingStarted.WhileSubscribed(5_000),
            )

    init {
        viewModelScope.launch {
            load()
        }
    }

    suspend fun load() {
        state.update { it.copy(isLoading = true, errorMessage = null) }
        postRepository.findBy(postId)
            .fold(
                ifLeft = { error ->
                    state.update { it.copy(isLoading = false, errorMessage = error.errorMessage) }
                },
                ifRight = { post ->
                    state.update { it.copy(isLoading = false, post = post) }
                },
            )
    }
}
