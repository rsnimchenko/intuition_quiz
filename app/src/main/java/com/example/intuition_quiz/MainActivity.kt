package com.example.intuition_quiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.intuition_quiz.ui.MainComponent
import com.example.intuition_quiz.ui.theme.AppTheme
import com.example.intuition_quiz.util.SharedPrefUtil
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.AndroidEntryPoint
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