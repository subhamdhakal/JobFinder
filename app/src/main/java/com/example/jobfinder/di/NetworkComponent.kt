package com.example.jobfinder.di

import com.example.jobfinder.api.GitHubJobApi
import com.example.jobfinder.api.GovJobsApi
import com.example.jobfinder.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface NetworkComponent {
    fun provideGitHubApiService() :GitHubJobApi
    fun provideGovApiService():GovJobsApi

}
