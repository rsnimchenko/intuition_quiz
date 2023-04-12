package com.example.intuition_quiz.ui.right_answer_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intuition_quiz.data.retrofit.NumbersRepository
import com.example.intuition_quiz.data.retrofit.RetrofitResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RightAnswerScreenViewModel @Inject constructor(
    savedState: SavedStateHandle,
    private val numbersRepository: NumbersRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RightAnswerScreenUiState())
    val uiState: StateFlow<RightAnswerScreenUiState> = _uiState

    init {
        val number = savedState.get<String>("number").orEmpty()
        getNumberInfo(number)
    }

    private fun getNumberInfo(number: String) {
        viewModelScope.launch {
            when(val response = numbersRepository.getNumberInfo(number)) {
                is RetrofitResponse.Failure -> {}
                is RetrofitResponse.Success -> { _uiState.update { it.copy(numberInfo = response.numberInfo) } }
            }
        }
    }
}

data class RightAnswerScreenUiState(
    val numberInfo: String = ""
)