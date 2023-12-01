package com.bracketcove.sckul.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bracketcove.sckul.R
import com.bracketcove.sckul.data.SckulUiState

@Composable
fun HomeScreen(
    uiState: State<SckulUiState>,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(visible = uiState.value.isShowingHome) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Text(
                stringResource(R.string.home),
                style = MaterialTheme.typography.headlineLarge,
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
fun HomeScreenPreview() {
    val viewModel: SckulViewModel = viewModel()
    HomeScreen(viewModel.uiState.collectAsState())
}