package com.bracketcove.sckul

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bracketcove.sckul.data.SckulUiState
import com.bracketcove.sckul.ui.ChatScreen
import com.bracketcove.sckul.ui.HomeScreen
import com.bracketcove.sckul.ui.ScheduleScreen
import com.bracketcove.sckul.ui.SckulViewModel
import com.bracketcove.sckul.ui.uitls.getScreenName

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SckulApp(viewModel: SckulViewModel = viewModel()) {
    val uiState = viewModel.uiState.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var isOpen by remember { mutableStateOf(false) }
    var isClosed by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = isOpen) {
        drawerState.open()
    }
    LaunchedEffect(key1 = isClosed) {
        drawerState.close()
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                uiState = uiState,
                onHomeClick = {
                    viewModel.navigateHome()
                    isClosed = !isClosed
                },
                onChatClick = {
                    viewModel.navigateChat()
                    isClosed = !isClosed
                },
                onScheduleClick = {
                    viewModel.navigateSchedule()
                    isClosed = !isClosed
                }
            )
        },
    ) {
        Scaffold(
            topBar = {
                SckulAppBar(
                    uiState = uiState,
                    onDrawerClick = { isOpen = !isOpen },
                )
            },
            floatingActionButton = {
                if (uiState.value.isShowingChat) {
                    NewChat(onEditClick = { })
                }
            }
        ) { innerPadding ->
            Column(Modifier.padding(innerPadding)) {
                HomeScreen(uiState = uiState)
                ChatScreen(viewModel = viewModel, uiState = uiState)
                ScheduleScreen(viewModel = viewModel, uiState = uiState)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SckulAppBar(
    uiState: State<SckulUiState>,
    onDrawerClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = stringResource(id = getScreenName(uiState)))
        },
        navigationIcon = {
            IconButton(onClick = onDrawerClick) {
                Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = colorScheme.primary,
            titleContentColor = colorScheme.onPrimary,
            actionIconContentColor = colorScheme.onPrimary,
            navigationIconContentColor = colorScheme.onPrimary,
        )
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DrawerContent(
    uiState: State<SckulUiState>,
    onChatClick: () -> Unit,
    onHomeClick: () -> Unit,
    onScheduleClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(
        topStart = 21.dp, topEnd = 10.dp,
        bottomStart = 10.dp, bottomEnd = 21.dp
    )
    ModalDrawerSheet(
        modifier = modifier,
        drawerShape = RoundedCornerShape(
            topEnd = 21.dp,
            bottomEnd = 21.dp
        )
    ) {
        // Navigation options
        Text(
            text = stringResource(id = R.string.app_name),
            style = typography.headlineLarge,
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp)
        )
        Divider()
        // Navigation options
        Column(modifier = Modifier.padding(8.dp)) {
            NavigationDrawerItem(
                label = { Text(text = stringResource(id = R.string.home)) },
                selected = uiState.value.isShowingHome,
                onClick = onHomeClick,
                shape = shape,
                icon = {
                    Icon(imageVector = Icons.Rounded.Home, contentDescription = null)
                }
            )
            NavigationDrawerItem(
                label = { Text(text = stringResource(id = R.string.chat)) },
                selected = uiState.value.isShowingChat,
                onClick = onChatClick,
                shape = shape,
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_chat),
                        contentDescription = null
                    )
                },
                badge = {
                    BadgedBox(
                        badge = {
                            Badge(
                                containerColor = Color.Transparent,
                                contentColor = LocalContentColor.current,
                            ) {
                                Text(text = "+99")
                            }
                        },
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) { }
                },
            )
            NavigationDrawerItem(
                label = { Text(text = stringResource(id = R.string.schedule)) },
                selected = uiState.value.isShowingSchedule,
                onClick = onScheduleClick,
                shape = shape,
                icon = {
                    Icon(imageVector = Icons.Rounded.DateRange, contentDescription = null)
                },
                badge = {
                    BadgedBox(
                        badge = {
                            Badge(
                                containerColor = Color.Transparent,
                                contentColor = LocalContentColor.current,
                            ) {
                                Text(text = "+99")
                            }
                        },
                        modifier = Modifier.padding(horizontal = 8.dp),
                    ) { }
                },
            )
        }
    }
}

@Composable
private fun NewChat(
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onEditClick,
        modifier = modifier
    ) {
        Icon(imageVector = Icons.Rounded.Edit, contentDescription = null)
    }
}

@Composable
@Preview(showBackground = true, device = "id:pixel_7_pro")
fun SckulAppPreview() {
    /*val viewModel: SckulViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState()*/
    SckulApp()
}