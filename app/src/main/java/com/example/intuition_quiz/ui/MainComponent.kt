package com.example.intuition_quiz.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.intuition_quiz.ui.home_screen.HomeScreen
import com.example.intuition_quiz.ui.right_answer_screen.RightAnswerScreen
import com.example.intuition_quiz.ui.web_view_screen.WebViewScreen
import com.example.intuition_quiz.ui.wrong_answer_screen.WrongAnswerScreen

@Composable
fun MainComponent(
    status: Boolean,
    link: String
) {
    val navController = rememberNavController()
    val toBackScreen: () -> Unit = { navController.popBackStack() }
    val toRightAnswerScreen =
        { number: String -> navController.navigate("${Screen.RIGHT_ANSWER.name}/$number") }
    val toWrongAnswerScreen =
        { number: String -> navController.navigate("${Screen.WRONG_ANSWER.name}/$number") }
    val toHomeScreen = {
        navController.popBackStack()
        navController.navigate(Screen.HOME.name)
    }

    NavHost(
        navController = navController,
        startDestination = if (status) Screen.WEB_VIEW.name else Screen.HOME.name
    ) {
        composable(
            route = Screen.HOME.name
        ) {
            HomeScreen(
                toRightAnswerScreen = toRightAnswerScreen,
                toWrongAnswerScreen = toWrongAnswerScreen
            )
        }
        composable(
            route = "${Screen.RIGHT_ANSWER.name}/{number}",
            arguments = listOf(navArgument("number") { type = NavType.StringType })
        ) {
            val number = it.arguments?.getString("number").orEmpty()
            RightAnswerScreen(
                guessesNumber = number,
                toBackScreen = toBackScreen
            )
        }
        composable(
            route = "${Screen.WRONG_ANSWER.name}/{number}",
            arguments = listOf(navArgument("number") { type = NavType.StringType })
        ) {
            val number = it.arguments?.getString("number").orEmpty()
            WrongAnswerScreen(
                guessesNumber = number,
                toBackScreen = toBackScreen
            )
        }
        composable(
            route = Screen.WEB_VIEW.name
        ) {
            WebViewScreen(
                url = link,
                toHomeScreen = toHomeScreen
            )
        }
    }
}

enum class Screen {
    HOME, RIGHT_ANSWER, WRONG_ANSWER, WEB_VIEW
}