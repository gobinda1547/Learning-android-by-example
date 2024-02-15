package com.gobinda.hilt.sampleproject3.module

import com.gobinda.hilt.sampleproject3.services.DataSource
import com.gobinda.hilt.sampleproject3.services.LocalDataSource
import com.gobinda.hilt.sampleproject3.services.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@InstallIn(ActivityComponent::class)
@Module
class ActivityLevelModule {

    /**
     * Since we are providing 2 bindings for the DataSource interface, that's why we
     * have to mention when to use which binding. That's why we have created 2 types
     * of Annotation [LocalSource] & [RemoteSource]. For this binding we have also
     * defined that anybody wants to have an object of [DataSource] with annotation
     * [LocalSource] will have an object of [LocalDataSource]
     */
    @Provides
    @ActivityScoped
    @LocalSource
    fun provideLocalDataSource(): DataSource {
        return LocalDataSource()
    }

    /**
     * Since we are providing 2 bindings for the DataSource interface, that's why we
     * have to mention when to use which binding. That's why we have created 2 types
     * of Annotation [LocalSource] & [RemoteSource]. For this binding we have also
     * defined that anybody wants to have an object of [DataSource] with annotation
     * [RemoteSource] will have an object of [RemoteDataSource]
     */
    @Provides
    @ActivityScoped
    @RemoteSource
    fun provideRemoteDataSource(): DataSource {
        return RemoteDataSource()
    }
}