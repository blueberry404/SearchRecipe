package com.anum.gtl_assignment.di

import com.anum.gtl_assignment.ui.main.MainAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ActivityComponent::class)
class MainActivityModule {

    @ActivityScoped
    @Provides fun getMainAdapter(): MainAdapter = MainAdapter()
}