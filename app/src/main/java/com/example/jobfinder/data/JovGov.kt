package com.example.jobfinder.data

data class JovGov(
    val id: String,
    val position_title: String,
    val organization_name: String,
    val rate_interval_code: String,
    val minimum: Int,
    val maximum: Int,
    val start_date: String,
    val end_date: String,
    val locations: List<String>,
    val url: String
)