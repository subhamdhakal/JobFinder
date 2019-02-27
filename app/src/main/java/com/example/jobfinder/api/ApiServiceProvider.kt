package com.example.jobfinder.api

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.example.jobfinder.di.DaggerNetworkComponent
import com.example.jobfinder.di.NetworkComponent
import com.example.jobfinder.module.NetworkModule
import com.example.jobfinder.utils.AppPreferences
import javax.inject.Inject

class ApiServiceProvider @Inject constructor(var appPreferences: AppPreferences) {
    var token = ""
    val TAG=ApiServiceProvider::class.java.simpleName
    @SuppressLint("LogNotTimber")
    fun getGitHubApiService(): GitHubJobApi {
        Log.e(TAG, "Token2 $token")
        var component: NetworkComponent = DaggerNetworkComponent
            .builder()
            .networkModule(NetworkModule(token))
            .build()
        return component.provideGitHubApiService()
    }
    fun getGovApiService(): GovJobsApi {
        Log.e(TAG, "Token2 $token")
        var component: NetworkComponent = DaggerNetworkComponent
            .builder()
            .networkModule(NetworkModule(token))
            .build()
        return component.provideGovApiService()
    }

}


