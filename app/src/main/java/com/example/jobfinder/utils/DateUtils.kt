package com.example.jobfinder.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.getFormattedStringFromTimeStamp(): String {
    val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
    val calendar = Calendar.getInstance()
    calendar.time = Date(this)
    val strDate = simpleDateFormat.format(calendar.time)
    return strDate

}

//Mon Feb 18 08:58:54 UTC 2019

