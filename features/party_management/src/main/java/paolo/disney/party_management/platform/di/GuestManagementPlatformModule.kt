package paolo.disney.party_management.platform.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import paolo.disney.party_management.domain.repositories.GuestManagementRepository
import paolo.disney.party_management.domain.use_cases.GetGuestsUseCase
import paolo.disney.party_management.domain.use_cases.RegisterGuestUseCase
import paolo.disney.party_management.platform.front.common.mappers.GuestByTypeHolderMapper
import paolo.disney.party_management.platform.front.common.mappers.GuestMapper


@Module
@InstallIn(ViewModelComponent::class)
object GuestManagementPlatformModule {

    // Use Cases
    @ViewModelScoped
    @Provides
    fun providesGetGuestsUseCase(guestManagementRepository: GuestManagementRepository) =
        GetGuestsUseCase(guestManagementRepository)

    @ViewModelScoped
    @Provides
    fun providesRegisterGuestUseCase(guestManagementRepository: GuestManagementRepository) =
        RegisterGuestUseCase(guestManagementRepository)

    // Mappers
    @Provides
    fun providesGuestMapper() =
        GuestMapper()

    @Provides
    fun providesGuestByTypeHolderMapper(guestMapper: GuestMapper) =
        GuestByTypeHolderMapper(guestMapper)

    // Dispatcher
    @Provides
    fun providesDispatcher() = Dispatchers.IO

}