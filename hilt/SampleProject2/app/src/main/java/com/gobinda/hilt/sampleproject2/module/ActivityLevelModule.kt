package com.gobinda.hilt.sampleproject2.module

import com.gobinda.hilt.sampleproject2.services.DataSource
import com.gobinda.hilt.sampleproject2.services.LocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@InstallIn(ActivityComponent::class)
@Module
abstract class ActivityLevelModule {

    /**
     * Binds annotation and Provides annotations are almost same but the only difference
     * is that Binds annotation are used to bind an interface with the actual class & hilt
     * must know how to create the object of the actual class. And the function which is
     * used to bind them should be an abstract class. And since only the abstract class
     * can have abstract method that's why the module class will also be abstract.
     *
     * Here in this project I have provided @Inject constructor for [LocalDataSource]
     */
    @Binds
    @ActivityScoped
    abstract fun bindDataSource(localDataSource: LocalDataSource): DataSource
}