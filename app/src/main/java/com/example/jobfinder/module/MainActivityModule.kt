package com.example.jobfinder.module

import com.example.jobfinder.data.repository.Repository
import com.example.jobfinder.vm.MainActivityVM
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule{
    @Provides
    fun providesMainActivityViewModel(repository: Repository): MainActivityVM {
        return MainActivityVM(repository)
    }
}