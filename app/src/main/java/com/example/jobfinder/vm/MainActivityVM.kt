package com.example.jobfinder.vm

import com.example.jobfinder.data.Data
import com.example.jobfinder.data.JobGitHub
import com.example.jobfinder.data.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject

class MainActivityVM @Inject constructor(var repository: Repository){

    fun getGitHubJobs():Observable<List<JobGitHub>>{
        return repository.getGitHubJobs()
    }

}