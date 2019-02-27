package com.example.jobfinder.data.repository

import android.util.Log
import com.example.jobfinder.api.ApiServiceProvider
import com.example.jobfinder.data.Job
import com.example.jobfinder.data.JobGitHub
import com.example.jobfinder.data.JovGov
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

class Repository @Inject constructor(var apiServiceProvider: ApiServiceProvider) {
    var jobs: MutableList<Job> = ArrayList()
    fun getGitHubJobs(): Observable<List<JobGitHub>> {
        return apiServiceProvider.getGitHubApiService().getJobsFromGithub()
            .subscribeOn(Schedulers.io())
            .doOnNext {
                convertGitHubJobToJob(it)
            }
    }
    fun convertGitHubJobToJob(gitHubJobs: List<JobGitHub>) {
        gitHubJobs.forEach {
            var job = Job(it.title, it.company, it.location, it.created_at)
            jobs.add(job)
        }
    }

    fun getGovJobs(): Observable<List<JovGov>> {
        return apiServiceProvider.getGovApiService().getJobFromGovApi()
    }

    fun getAllJobs() {
    }

}