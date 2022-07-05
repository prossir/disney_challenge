package paolo.disney.party_management.domain.use_cases

import androidx.lifecycle.LiveData
import paolo.disney.party_management.domain.entities.Guest
import paolo.disney.party_management.domain.repositories.GuestManagementRepository


class GetGuestsUseCase(private val guestManagementRepository: GuestManagementRepository) {

    suspend operator fun invoke(): LiveData<List<Guest>> {
        return guestManagementRepository.getAllGuests()
    }

}