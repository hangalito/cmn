package com.bracketcove.sckul.ui

import androidx.lifecycle.ViewModel
import com.bracketcove.sckul.data.SckulUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SckulViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SckulUiState())
    val uiState: StateFlow<SckulUiState> = _uiState.asStateFlow()

    fun navigateHome() {
        _uiState.update { currentState ->
            currentState.copy(
                isShowingHome = true,
                isShowingChat = false,
                isShowingSchedule = false
            )
        }
    }

    fun navigateChat() {
        _uiState.update { currentState ->
            currentState.copy(
                isShowingHome = false,
                isShowingChat = true,
                isShowingSchedule = false
            )
        }
    }

    fun navigateSchedule() {
        _uiState.update { currentState ->
            currentState.copy(
                isShowingHome = false,
                isShowingChat = false,
                isShowingSchedule = true
            )
        }
    }
}