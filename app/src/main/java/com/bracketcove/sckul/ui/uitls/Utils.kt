package com.bracketcove.sckul.ui.uitls

import androidx.compose.runtime.State
import com.bracketcove.sckul.R
import com.bracketcove.sckul.data.SckulUiState

fun getScreenName(uiState: State<SckulUiState>): Int {
    return  when {
        uiState.value.isShowingHome -> R.string.home
        uiState.value.isShowingChat -> R.string.chat
        uiState.value.isShowingSchedule -> R.string.schedule
        else -> R.string.home
    }
}