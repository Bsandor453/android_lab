package hu.bme.cryptochecker.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Repository {

    @Singleton
    @Provides
    @Named("DummyRepositoryService")
    fun dummyRepositoryService() = "This is a dummy repository service"

}