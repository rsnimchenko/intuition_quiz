package com.example.intuition_quiz.ui.home_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import com.example.intuition_quiz.util.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class HomeScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState

    init {
        gameStateInitialize()
    }

    fun checkAnswer() {
        try {
            if (_uiState.value.textInField.toInt() == _uiState.value.guessesNumber)
                gameStateUpdate(GameState.GameWin)
            else decreaseTriesNumber()
            textInFieldUpdate("")
        } catch (e: java.lang.NumberFormatException) {
            decreaseTriesNumber()
        }
    }

    fun gameStateInitialize() {
        val guessesStartRange = getRand(Constants.START_RANGE, Constants.END_RANGE)
        val guessesNumber = getRand(guessesStartRange, guessesStartRange + Constants.COUNT_IN_RANGE)
        _uiState.update {
            it.copy(
                gameState = GameState.Initialize,
                guessesStartRange = guessesStartRange,
                guessesNumber = guessesNumber,
                textInField = "",
                triesNumber = 3
            )
        }
    }

    fun textInFieldUpdate(textInField: String) =
        _uiState.update { it.copy(textInField = textInField) }

    private fun decreaseTriesNumber() {
        if (_uiState.value.triesNumber == 1) {
            gameStateUpdate(GameState.GameOver)
        } else {
            triesNumberUpdate(_uiState.value.triesNumber - 1)
        }
    }

    private fun gameStateUpdate(gameState: GameState) =
        _uiState.update { it.copy(gameState = gameState) }

    private fun triesNumberUpdate(triesNumber: Int) =
        _uiState.update { it.copy(triesNumber = triesNumber) }

    private fun getRand(from: Int, until: Int) = Random.nextInt(from, until)
}

data class HomeScreenUiState(
    val triesNumber: Int = 3,
    val gameState: GameState = GameState.Initialize,
    val guessesStartRange: Int = 0,
    val guessesNumber: Int = 0,
    val textInField: String = ""
)

sealed class GameState {
    object Initialize : GameState()
    object GameOver : GameState()
    object GameWin : GameState()
}