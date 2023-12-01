package com.bracketcove.sckul.data


/** Data class representing the ui state of the app.
 *
 * It has a single boolean value that shows either the is
 * on the home screen or not. */
data class SckulUiState(
    val isShowingHome: Boolean = true,
    val isShowingChat: Boolean = false,
    val isShowingSchedule: Boolean = false
)