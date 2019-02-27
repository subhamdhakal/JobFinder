package com.example.jobfinder.activity

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.jobfinder.R
import com.example.jobfinder.adapter.JobsAdapter
import com.example.jobfinder.api.LocationInterface
import com.example.jobfinder.utils.LocationUtil
import com.example.jobfinder.vm.MainActivityVM
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val TAG=MainActivity::class.simpleName
    @Inject
    lateinit var mainActivityVM: MainActivityVM
    lateinit var adapter: JobsAdapter
    var locationUtil=LocationUtil(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
        setSupportActionBar(toolbar)
        var adapter:JobsAdapter
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        search_view_search_job.queryHint="Search"

        nav_view.setNavigationItemSelectedListener(this)
        adapter=JobsAdapter(this,object:JobsAdapter.ClickListener{
            override fun clickedOnJob(url: String) {
                val intent=Intent(this@MainActivity,JobDetail::class.java)
                intent.putExtra("url",url)
                startActivity(intent)
            }
        })
        recycler_view_jobs.adapter=adapter
        recycler_view_jobs.layoutManager = LinearLayoutManager(this)
        fetchLocation()
        search_view_search_job.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
            override fun onQueryTextSubmit(p0: String?): Boolean {
                Toast.makeText(this@MainActivity, "Submitted:$p0" ,Toast.LENGTH_SHORT).show()
                mainActivityVM.getGitHubJobs(p0,null,null)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        adapter.setData(it)
                    },{
                        it.printStackTrace()
                    })
                return false
            }
        })
        mainActivityVM.getGitHubJobs(null,null,null)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                adapter.setData(it)
            },{
                it.printStackTrace()
            })


    }
    fun fetchLocation(){
        locationUtil= LocationUtil(this)
        locationUtil.setListener(object :LocationInterface{
            override fun getLocation(location: Location?) {
                Toast.makeText(this@MainActivity,"location:$location",Toast.LENGTH_SHORT).show()
            }
        })
        locationUtil.Permission()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {
            }
            R.id.nav_slideshow -> {
            }
            R.id.nav_manage -> {
            }
            R.id.nav_share -> {
            }
            R.id.nav_send -> {
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
