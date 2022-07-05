package paolo.disney.foundation.database.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import paolo.disney.foundation.database.providers.DaoProvider
import paolo.disney.foundation.database.providers.DatabaseProvider
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabaseProvider(@ApplicationContext context: Context) =
        DatabaseProvider(context)

    @Singleton
    @Provides
    fun providesDaoProvider(databaseProvider: DatabaseProvider) =
        DaoProvider(databaseProvider)

}