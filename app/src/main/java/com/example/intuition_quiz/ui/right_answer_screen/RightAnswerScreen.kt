package com.example.intuition_quiz.ui.right_answer_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.intuition_quiz.R
import com.example.intuition_quiz.ui.InfoComponent
import com.example.intuition_quiz.ui.Toolbar

@Composable
fun RightAnswerScreen(
    guessesNumber: String,
    toBackScreen: () -> Unit,
    rightAnswerScreenViewModel: RightAnswerScreenViewModel = hiltViewModel()
) {
    val screenState = rightAnswerScreenViewModel.uiState.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Toolbar(
                label = stringResource(id = R.string.game_win),
                showBackButton = true,
                onClickBackButton = toBackScreen
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            InfoComponent(
                guessesNumber = guessesNumber,
                infoText = screenState.value.numberInfo,
                toBackScreen = toBackScreen,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

}