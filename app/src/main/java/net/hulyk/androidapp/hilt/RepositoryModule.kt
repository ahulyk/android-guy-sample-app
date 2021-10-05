package net.hulyk.androidapp.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.hulyk.androidapp.repo.PersonRepositoryAssetImpl
import net.hulyk.androidapp.repo.PersonRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun providePeopleRepository(repository: PersonRepositoryAssetImpl): PersonRepository

}