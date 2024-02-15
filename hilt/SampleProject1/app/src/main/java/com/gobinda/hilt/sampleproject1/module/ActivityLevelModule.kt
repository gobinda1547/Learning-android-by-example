package com.gobinda.hilt.sampleproject1.module

import com.gobinda.hilt.sampleproject1.services.DataSource
import com.gobinda.hilt.sampleproject1.services.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@InstallIn(ActivityComponent::class)
@Module
class ActivityLevelModule {

    @Provides
    @ActivityScoped
    fun provideDataSource(): DataSource {
        return LocalDataSource()
    }
}