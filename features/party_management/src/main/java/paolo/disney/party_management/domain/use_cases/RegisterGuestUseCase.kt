package paolo.disney.party_management.domain.use_cases

import paolo.disney.party_management.domain.entities.Guest
import paolo.disney.party_management.domain.repositories.GuestManagementRepository


class RegisterGuestUseCase(private val guestManagementRepository: GuestManagementRepository) {

    suspend operator fun invoke(newGuest: Guest) {
        guestManagementRepository.save(newGuest)
    }

}