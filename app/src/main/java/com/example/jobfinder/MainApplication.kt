package com.example.jobfinder


import android.app.Activity
import android.app.Application
import android.support.multidex.MultiDexApplication
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatDelegate
import com.example.jobfinder.di.DaggerAppComponent
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainApplication : MultiDexApplication(), HasActivityInjector {


    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

//    @Inject
//    lateinit var fragDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
        Stetho.initializeWithDefaults(this)

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector

//    override fun supportFragmentInjector(): AndroidInjector<Fragment> =fragDispatchingAndroidInjector
}