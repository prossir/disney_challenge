package paolo.disney.party_management.domain.repositories

import androidx.lifecycle.LiveData
import paolo.disney.party_management.domain.entities.Guest

interface GuestManagementRepository {

    suspend fun getAllGuests(): LiveData<List<Guest>>
    suspend fun save(newGuest: Guest)

}