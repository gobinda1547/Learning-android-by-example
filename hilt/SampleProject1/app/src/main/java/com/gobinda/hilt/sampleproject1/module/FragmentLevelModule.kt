package com.gobinda.hilt.sampleproject1.module

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
class FragmentLevelModule {

}