package com.example.jobfinder.vm

import com.example.jobfinder.data.JobGitHub
import com.example.jobfinder.data.repository.Repository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityVM @Inject constructor(var repository: Repository) {

    fun getGitHubJobs(): Observable<List<JobGitHub>> {
        return repository.getGitHubJobs()
            .flatMap {
                getGovJob(it)
            }
    }

    private fun getGovJob(githubJob: List<JobGitHub>): Observable<List<JobGitHub>> {
        return repository.getGovJobs()
            .subscribeOn(Schedulers.io())
            .map {
                val jobList: MutableList<JobGitHub> = githubJob.toMutableList()
                it.forEach { jovGov ->
                    val job = JobGitHub(
                        url = jovGov.url,
                        location = if (!jovGov.locations.isNullOrEmpty()) jovGov.locations[0] else "",
                        title = jovGov.position_title,
                        company = jovGov.organization_name,
                        company_logo = "https://kpmg.contacthr.com/67881010",
                        company_url = "",
                        created_at = jovGov.start_date,
                        type = ""
                    )
                    jobList.add(job)
                }
                jobList
            }.flatMap {
                Observable.fromCallable { it }
            }
    }

}