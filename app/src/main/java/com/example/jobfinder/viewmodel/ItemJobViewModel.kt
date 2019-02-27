package com.example.jobfinder.viewmodel

import com.example.jobfinder.data.JobGitHub

class ItemJobViewModel{
    var gitHub:JobGitHub?=null

    fun getCompanyName():String{
        return gitHub!!.company
    }

}