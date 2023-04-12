package com.example.intuition_quiz.ui.web_view_screen

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.intuition_quiz.util.SharedPrefUtil
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState

@Composable
fun WebViewScreen(
    url: String,
    toHomeScreen: () -> Unit
) {
    val state = rememberWebViewState(url = url)
    val navigator = rememberWebViewNavigator()

    SharedPrefUtil.writeLastUrl(state.lastLoadedUrl.orEmpty())

    BackHandler {
        if (navigator.canGoBack) navigator.navigateBack()
        else toHomeScreen()
    }

    WebView(
        state = state,
        navigator = navigator,
        modifier = Modifier.fillMaxSize(),
        onCreated = { webView ->
            webView.settings.javaScriptEnabled = true
        }
    )
}