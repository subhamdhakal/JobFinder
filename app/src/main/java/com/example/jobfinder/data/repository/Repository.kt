package com.example.jobfinder.data.repository

import com.example.jobfinder.api.ApiServiceProvider
import com.example.jobfinder.data.JobGitHub
import com.example.jobfinder.data.JovGov
import io.reactivex.Observable
import java.text.FieldPosition
import javax.inject.Inject

class Repository @Inject constructor(var apiServiceProvider: ApiServiceProvider) {


    fun getGitHubJobs(position: String?,lat:String?,long:String?): Observable<List<JobGitHub>> {
        return apiServiceProvider.getGitHubApiService().getJobsFromGithub(position,lat,long)
    }
    fun getGovJobs():Observable<List<JovGov>>{
        return apiServiceProvider.getGovApiService().getJobFromGovApi()
    }
}