package com.example.jobfinder.data


data class Data(var data:List<JobGitHub>)

data class JobGitHub(
    var type: String,
    var url: String,
    var created_at: String,
    var company: String,
    var company_url: String,
    var location:String,
    var company_logo:String,
    var title:String
)