package com.example.intuition_quiz.ui.home_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.intuition_quiz.R
import com.example.intuition_quiz.util.Constants
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HomeScreenBody(
    homeScreenUiState: StateFlow<HomeScreenUiState>,
    toRightAnswerScreen: (String) -> Unit,
    toWrongAnswerScreen: (String) -> Unit,
    checkAnswer: () -> Unit,
    textInFieldUpdate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val screenState = homeScreenUiState.collectAsState()

    when (screenState.value.gameState) {
        GameState.GameOver -> toWrongAnswerScreen("${screenState.value.guessesNumber}")
        GameState.GameWin -> toRightAnswerScreen("${screenState.value.guessesNumber}")
        else -> {}
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TriesCount(
            triesNumber = screenState.value.triesNumber,
            modifier = Modifier.align(Alignment.TopEnd)
        )
        GuessNumberField(
            guessesStartRange = screenState.value.guessesStartRange,
            textInField = screenState.value.textInField,
            checkAnswer = checkAnswer,
            textInFieldUpdate = textInFieldUpdate,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun TriesCount(
    triesNumber: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = R.string.number_of_tries, triesNumber),
        modifier = modifier.padding(16.dp),
        style = MaterialTheme.typography.h3,
        color = MaterialTheme.colors.onBackground
    )
}

@Composable
fun GuessNumberField(
    guessesStartRange: Int,
    textInField: String,
    checkAnswer: () -> Unit,
    textInFieldUpdate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(
                    id = R.string.guess_the_number_range,
                    guessesStartRange,
                    guessesStartRange + Constants.COUNT_IN_RANGE
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = textInField,
                onValueChange = textInFieldUpdate,
                placeholder = {
                    Text(stringResource(id = R.string.enter_number_placeholder))
                },
                textStyle = MaterialTheme.typography.h2,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { checkAnswer() }
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { checkAnswer() },
                modifier = Modifier.fillMaxWidth(),
                enabled = textInField.isNotEmpty()
            ) {
                Text(text = stringResource(id = R.string.guess))
            }
        }
    }

}