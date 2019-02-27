package com.example.jobfinder.api

import com.example.jobfinder.data.JovGov
import io.reactivex.Observable
import retrofit2.http.GET

interface GovJobsApi {
    @GET("search.json")
    fun getJobFromGovApi(): Observable<List<JovGov>>
}