package paolo.disney.party_management.platform.front.common.dtos

import paolo.disney.party_management.platform.front.common.validators.PartyValidator


class PartyModel(
    private val guests: ArrayList<GuestModel>
) {

    val validator = PartyValidator()

    fun registerOrUnregisterGuest(guest: GuestModel) {
        val registeredGuest = guests.find {
            it.id == guest.id
        }

        registeredGuest?.apply {
            registeredGuest.isChecked = guest.isChecked
        } ?: kotlin.run {
            guests.add(guest)
        }

        validator.checkPartyIntegrity(guests)
    }

    fun reset() {
        guests.clear()

        validator.checkPartyIntegrity(guests)
    }

    companion object {
        fun new() = PartyModel(
            arrayListOf()
        )
    }


}