package com.example.jobfinder.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.jobfinder.R
import kotlinx.android.synthetic.main.activity_job_detail.*

class JobDetail : AppCompatActivity() {
    val TAG=JobDetail::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_detail)
        val intent=intent
        Log.d(TAG,""+intent.getStringExtra("url"))
        web_view_job_detail.loadUrl(intent.getStringExtra("url"))
    }
}
