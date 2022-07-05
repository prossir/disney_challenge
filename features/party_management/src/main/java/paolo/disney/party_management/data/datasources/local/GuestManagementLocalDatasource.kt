package paolo.disney.party_management.data.datasources.local

import androidx.lifecycle.LiveData
import paolo.disney.foundation.database.dtos.GuestEntity
import paolo.disney.foundation.database.providers.DaoProvider


class GuestManagementLocalDatasource(
    daoProvider: DaoProvider
) {

    private val guestDao = daoProvider.getGuestDao()

    fun retrieveAllLive(): LiveData<List<GuestEntity>> {
        return guestDao.findAllLive()
    }

    fun save(newGuest: GuestEntity) {
        guestDao.insert(newGuest)
    }

}