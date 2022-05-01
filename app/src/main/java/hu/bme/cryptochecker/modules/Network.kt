package hu.bme.cryptochecker.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Network {

    @Singleton
    @Provides
    @Named("DummyNetworkService")
    fun dummyNetworkService() = "This is a dummy network service"

}