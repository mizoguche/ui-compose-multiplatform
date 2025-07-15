package dev.mizoguche.composegram.ui.startup

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

sealed class StartupUiState {
    data object Loading : StartupUiState()

    data object Idle : StartupUiState()
}

sealed class StartupEvent {
    data object NavigateToHome : StartupEvent()
}

class StartupViewModel : ViewModel() {
    data class ViewModelState(
        val isLoading: Boolean = true,
    ) {
        fun toUiState(): StartupUiState {
            if (isLoading) return StartupUiState.Loading
            return StartupUiState.Idle
        }
    }

    private val state = MutableStateFlow(ViewModelState())
    val uiState: StateFlow<StartupUiState> =
        state
            .map { it.toUiState() }
            .stateIn(
                scope = viewModelScope,
                initialValue = StartupUiState.Loading,
                started = SharingStarted.WhileSubscribed(5_000),
            )

    private val _navigationEvent = MutableSharedFlow<StartupEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            load()
        }
    }

    private suspend fun load() {
        // Startup process(e.g. checking update, sign in)
        delay(1_000)

        state.emit(ViewModelState(false))
    }

    fun signIn() {
        viewModelScope.launch {
            _navigationEvent.emit(StartupEvent.NavigateToHome)
        }
    }
}
