package com.example.jobfinder.api

import com.example.jobfinder.data.JobGitHub
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET

interface GitHubJobApi {
    @GET("positions.json")
    fun getJobsFromGithub(): Observable<List<JobGitHub>>
}