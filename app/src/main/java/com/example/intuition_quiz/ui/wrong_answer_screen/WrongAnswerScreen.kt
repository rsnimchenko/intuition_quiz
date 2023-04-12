package com.example.intuition_quiz.ui.wrong_answer_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.intuition_quiz.R
import com.example.intuition_quiz.ui.InfoComponent
import com.example.intuition_quiz.ui.Toolbar

@Composable
fun WrongAnswerScreen(
    guessesNumber: String,
    toBackScreen: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Toolbar(
                label = stringResource(id = R.string.game_over),
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
                infoText = stringResource(id = R.string.didnt_guess),
                toBackScreen = toBackScreen,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

}