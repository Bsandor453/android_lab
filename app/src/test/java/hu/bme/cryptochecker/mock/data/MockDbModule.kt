package hu.bme.cryptochecker.mock.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.cryptochecker.persistence.CryptocurrencyDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MockDbModule {
    @Provides
    @Singleton
    fun provideMockCryptocurrencyDao(): CryptocurrencyDao = MockCryptocurrencyDao()
}