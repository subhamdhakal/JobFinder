package com.example.jobfinder.data.repository

import com.example.jobfinder.api.ApiServiceProvider
import com.example.jobfinder.data.JobGitHub
import com.example.jobfinder.data.JovGov
import io.reactivex.Observable
import javax.inject.Inject

class Repository @Inject constructor(var apiServiceProvider: ApiServiceProvider) {


    fun getGitHubJobs(): Observable<List<JobGitHub>> {
        return apiServiceProvider.getGitHubApiService().getJobsFromGithub()
    }
    fun getGovJobs():Observable<List<JovGov>>{
        return apiServiceProvider.getGovApiService().getJobFromGovApi()
    }
}