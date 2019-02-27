package com.example.jobfinder.di

import com.example.jobfinder.activity.MainActivity
import com.example.jobfinder.module.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder{
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity():MainActivity

}