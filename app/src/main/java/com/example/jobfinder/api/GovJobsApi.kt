package com.example.jobfinder.api

import com.example.jobfinder.data.JovGov
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GovJobsApi {
    @GET("search.json")
    fun getJobFromGovApi(
        @Query("query") query: String?
        , @Query("organization_ids") organization_ids: String?
        , @Query("lat_lon") lat_lon: String?

    ): Observable<List<JovGov>>
}