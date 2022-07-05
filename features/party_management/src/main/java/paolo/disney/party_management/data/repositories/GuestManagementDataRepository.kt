package paolo.disney.party_management.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import paolo.disney.party_management.data.datasources.local.GuestManagementLocalDatasource
import paolo.disney.party_management.data.mappers.local.GuestLocalMapper
import paolo.disney.party_management.domain.entities.Guest
import paolo.disney.party_management.domain.repositories.GuestManagementRepository


class GuestManagementDataRepository(
    private val guestManagementLocalDatasource: GuestManagementLocalDatasource,
    private val guestLocalMapper: GuestLocalMapper
): GuestManagementRepository {

    override suspend fun getAllGuests(): LiveData<List<Guest>> {
        return Transformations.map(guestManagementLocalDatasource.retrieveAllLive()) {
            guestLocalMapper.map(it)
        }
    }

    override suspend fun save(newGuest: Guest) {
        guestManagementLocalDatasource.save(
            guestLocalMapper.reverseMap(newGuest)
        )
    }

}