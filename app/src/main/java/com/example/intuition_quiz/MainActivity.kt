package com.example.intuition_quiz

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.window.SplashScreen
import android.window.SplashScreen.OnExitAnimationListener
import android.window.SplashScreenView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.intuition_quiz.ui.MainComponent
import com.example.intuition_quiz.ui.theme.AppTheme
import com.example.intuition_quiz.util.SharedPrefUtil
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var remoteConfig: FirebaseRemoteConfig
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var status = remoteConfig.getBoolean("status")
        var link = remoteConfig.getString("link")

        SharedPrefUtil.readLastUrl().let {
            if (it.isNotEmpty()) {
                status = true
                link = it
            }
        }
        setContent {
            AppTheme {
                MainComponent(status, link)
            }
        }
    }
}