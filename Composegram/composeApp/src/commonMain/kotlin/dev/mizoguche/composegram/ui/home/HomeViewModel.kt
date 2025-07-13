package dev.mizoguche.composegram.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data object Idle : HomeUiState()
}

class HomeViewModel : ViewModel() {
    data class ViewModelState(
        val isLoading: Boolean = true,
    ) {
        fun toUiState(): HomeUiState {
            if (isLoading) return HomeUiState.Loading
            return HomeUiState.Idle
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
}