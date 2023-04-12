package com.example.intuition_quiz.hilt

import com.example.intuition_quiz.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteConfigModule {
    @Provides
    @Singleton
    fun provideConfigSettings(): FirebaseRemoteConfigSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = 1
    }

    @Provides
    @Singleton
    fun provideRemoteConfig(settings: FirebaseRemoteConfigSettings): FirebaseRemoteConfig = Firebase.remoteConfig.apply {
        setConfigSettingsAsync(settings)
        setDefaultsAsync(R.xml.remote_config_defaults)
    }
}