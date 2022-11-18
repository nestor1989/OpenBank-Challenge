package com.idea3d.open_bank_challengue.core.di

import com.idea3d.open_bank_challengue.data.DataSourceImpl
import com.idea3d.open_bank_challengue.domain.DataSource
import com.idea3d.open_bank_challengue.repository.Repo
import com.idea3d.open_bank_challengue.repository.RepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindRepoImpl(repoImpl: RepoImpl):Repo

    @Binds
    abstract fun dataSourceImpl(dataSourceImpl: DataSourceImpl):DataSource

}