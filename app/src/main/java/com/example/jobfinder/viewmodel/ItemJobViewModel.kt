package com.example.jobfinder.viewmodel

import com.example.jobfinder.data.JobGitHub


class ItemJobViewModel {
    var gitHub: JobGitHub? = null

    fun getCompanyName(): String {
        return gitHub!!.company
    }

    fun getTitle(): String {
        return gitHub!!.title
    }

    fun getLocation(): String {
        return gitHub!!.location
    }

    fun getPostDate(): String {
        return gitHub!!.created_at
    }


}