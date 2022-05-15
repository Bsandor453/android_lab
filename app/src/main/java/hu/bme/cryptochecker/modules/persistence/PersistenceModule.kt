package hu.bme.cryptochecker.modules.persistence

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.cryptochecker.persistence.CryptoDatabase
import hu.bme.cryptochecker.persistence.CryptocurrencyDao
import hu.bme.cryptochecker.persistence.HistoryDao
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {

    @Singleton
    @Provides
    fun provideCryptoDb(context: Application) : CryptoDatabase {
        return CryptoDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    @Named("CryptoDao")
    fun provideCryptocurrencyDao(database: CryptoDatabase): CryptocurrencyDao {
        return database.cryptoDao()
    }

    @Singleton
    @Provides
    @Named("HistoryDao")
    fun provideHistoryDao(database: CryptoDatabase): HistoryDao {
        return database.historyDao()
    }

}