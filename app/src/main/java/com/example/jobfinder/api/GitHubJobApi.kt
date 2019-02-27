package com.example.jobfinder.api

import com.example.jobfinder.data.JobGitHub
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubJobApi {
    @GET("positions.json")
    fun getJobsFromGithub(
        @Query("description") description: String?
        , @Query("lat") lat: String?
        , @Query("long") long: String?
    ): Observable<List<JobGitHub>>
}