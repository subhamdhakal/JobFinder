package com.example.jobfinder.data


data class Data(var data:List<JobGitHub>)

data class JobGitHub(
    val company: String,
    val company_logo: String,
    val company_url: String,
    var created_at: String,
    val location: String,
    val title: String,
    val type: String,
    val url: String
)