package dev.mizoguche.composegram.ui.userprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.mizoguche.composegram.domain.user.User
import dev.mizoguche.composegram.domain.user.UserId
import dev.mizoguche.composegram.domain.user.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class UserProfileUiState {
    data object Loading : UserProfileUiState()

    data class Idle(
        val user: User,
    ) : UserProfileUiState()

    data object Error : UserProfileUiState()
}

class UserProfileViewModel(
    private val userRepository: UserRepository,
    private val userId: UserId,
) : ViewModel() {
    data class ViewModelState(
        val isLoading: Boolean = true,
        val user: User? = null,
        val errorMessage: String? = null,
    ) {
        fun toUiState(): UserProfileUiState =
            when {
                isLoading -> UserProfileUiState.Loading
                errorMessage != null -> UserProfileUiState.Error
                user != null -> UserProfileUiState.Idle(user)
                else -> UserProfileUiState.Error
            }
    }

    private val state = MutableStateFlow(ViewModelState())
    val uiState: StateFlow<UserProfileUiState> =
        state
            .map { it.toUiState() }
            .stateIn(
                scope = viewModelScope,
                initialValue = UserProfileUiState.Loading,
                started = SharingStarted.WhileSubscribed(5_000),
            )

    init {
        viewModelScope.launch {
            load()
        }
    }

    suspend fun load() {
        state.update { it.copy(isLoading = true, errorMessage = null) }
        userRepository.findBy(userId)
            .fold(
                ifLeft = { error ->
                    state.update { it.copy(isLoading = false, errorMessage = error.errorMessage) }
                },
                ifRight = { user ->
                    state.update { it.copy(isLoading = false, user = user) }
                },
            )
    }
}
