package com.cmn.ui.screens.chat

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cmn.R
import com.cmn.data.SckulUiState
import com.cmn.ui.SckulViewModel

@Composable
fun ChatScreen(
    viewModel: SckulViewModel,
    uiState: State<SckulUiState>,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.navigateHome()
    }
    AnimatedVisibility(visible = uiState.value.isShowingChat) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Text(
                stringResource(R.string.chat),
                style = typography.headlineLarge,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(
                        Alignment.Center
                    )
            )
        }
    }
}

@Composable
@Preview(showBackground = true, device = "id:pixel_7_pro")
fun ChatScreenPreview() {
    val viewModel: SckulViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState()
    ChatScreen(viewModel, uiState)
}
