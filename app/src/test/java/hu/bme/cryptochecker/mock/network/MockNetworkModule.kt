package hu.bme.cryptochecker.mock.network

import dagger.hilt.testing.TestInstallIn
import dagger.Module
import dagger.hilt.components.SingletonComponent
import hu.bme.cryptochecker.modules.network.NetworkModule
import okhttp3.HttpUrl

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
class MockNetworkModule: NetworkModule() {

    override fun baseUrl(): HttpUrl {
        return MockServer.server.url("http://localhost/")
    }

}