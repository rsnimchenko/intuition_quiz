package com.example.intuition_quiz.ui.home_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.intuition_quiz.R
import com.example.intuition_quiz.ui.Toolbar

@Composable
fun HomeScreen(
    toRightAnswerScreen: (String) -> Unit,
    toWrongAnswerScreen: (String) -> Unit,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    BackHandler {}

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Toolbar(label = stringResource(id = R.string.guess_number))
        }
    ) { innerPadding ->
        HomeScreenBody(
            homeScreenUiState = homeScreenViewModel.uiState,
            toRightAnswerScreen = { homeScreenViewModel.gameStateInitialize();toRightAnswerScreen(it) },
            toWrongAnswerScreen = { homeScreenViewModel.gameStateInitialize();toWrongAnswerScreen(it) },
            checkAnswer = homeScreenViewModel::checkAnswer,
            textInFieldUpdate = homeScreenViewModel::textInFieldUpdate,
            modifier = Modifier.padding(innerPadding)
        )
    }
}