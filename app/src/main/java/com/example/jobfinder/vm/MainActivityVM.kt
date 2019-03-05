package com.example.jobfinder.vm

import android.os.Build
import android.support.annotation.RequiresApi
import com.example.jobfinder.data.JobGitHub
import com.example.jobfinder.data.repository.Repository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.time.LocalDate
import javax.inject.Inject

class MainActivityVM @Inject constructor(var repository: Repository) {

    var query:String?=""
    var organization_ids:String?=""
    var lat_lon:String?=""

    @RequiresApi(Build.VERSION_CODES.O)
    fun getGitHubJobs(position:String?, provider:String?, lat:String?, long:String?): Observable<List<JobGitHub>> {
        query=position
        organization_ids=provider
        lat_lon= "$lat,$long"
        return repository.getGitHubJobs(position,lat,long)
            .doOnNext {
                //Mon Feb 18 08:58:54 UTC 2019
                it.forEach {
                    it.created_at=SimpleDateFormat("EEE MMM dd HH:mm:ss 'UTC' yyyy").parse(it.created_at).time.toString()
                }

            }
            .flatMap {
                getGovJob(it)
            }

    }

    private fun getGovJob(githubJob: List<JobGitHub>): Observable<List<JobGitHub>> {
        return repository.getGovJobs(query,organization_ids,lat_lon)
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
                        created_at = SimpleDateFormat("yyyy-MM-dd").parse(jovGov.start_date).time.toString(),
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