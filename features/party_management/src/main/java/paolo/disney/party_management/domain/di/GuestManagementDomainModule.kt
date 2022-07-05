package paolo.disney.party_management.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import paolo.disney.foundation.database.providers.DaoProvider
import paolo.disney.party_management.data.datasources.local.GuestManagementLocalDatasource
import paolo.disney.party_management.data.mappers.local.GuestLocalMapper
import paolo.disney.party_management.data.repositories.GuestManagementDataRepository
import paolo.disney.party_management.domain.repositories.GuestManagementRepository


@Module
@InstallIn(ActivityRetainedComponent::class)
object  GuestManagementDomainModule {

    // Mappers
    // Local
    @Provides
    fun providesGuestLocalMapper() = GuestLocalMapper()

    // Datasource
    // Local
    @Provides
    fun providesGuestManagementLocalDatasource(daoProvider: DaoProvider) =
        GuestManagementLocalDatasource(daoProvider)

    // Repository
    @ActivityRetainedScoped
    @Provides
    fun providesCurrencyExchangeRepository(
        guestManagementLocalDatasource: GuestManagementLocalDatasource,
        guestLocalMapper: GuestLocalMapper,
    ) : GuestManagementRepository =
        GuestManagementDataRepository(guestManagementLocalDatasource, guestLocalMapper)

}