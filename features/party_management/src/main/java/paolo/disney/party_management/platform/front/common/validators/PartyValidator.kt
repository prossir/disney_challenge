package paolo.disney.party_management.platform.front.common.validators

import androidx.lifecycle.MutableLiveData
import paolo.disney.party_management.platform.front.common.dtos.GuestModel


class PartyValidator {
    val hasAtLeastOneGuestWithReservations: MutableLiveData<Boolean> = MutableLiveData(false)
    val allGuestHaveReservations: MutableLiveData<Boolean> = MutableLiveData(false)
    val canTryConfirmingParty: MutableLiveData<Boolean> = MutableLiveData(false)

    fun checkPartyIntegrity(guests: ArrayList<GuestModel>) {
        canTryConfirmingParty.postValue(guests.any { it.isChecked })
        val guestWithReservations = guests.filter { it.hasAReservation && it.isChecked }
        if(guestWithReservations.isNotEmpty()) {
            hasAtLeastOneGuestWithReservations.postValue(true)
            if(guestWithReservations.size == guests.size) {
                allGuestHaveReservations.postValue(true)
            }
            else {
                allGuestHaveReservations.postValue(false)
            }
        }
        else {
            hasAtLeastOneGuestWithReservations.postValue(false)
        }
    }

}