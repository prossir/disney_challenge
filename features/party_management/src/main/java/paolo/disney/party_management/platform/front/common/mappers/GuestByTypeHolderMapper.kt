package paolo.disney.party_management.platform.front.common.mappers

import paolo.disney.foundation.database.dtos.enums.GuestStatusEnum
import paolo.disney.foundation.mappers.UnaryMapper
import paolo.disney.party_management.domain.entities.Guest
import paolo.disney.party_management.platform.front.list_guests.adapter.GuestByTypeHolder


class GuestByTypeHolderMapper(
    private val guestMapper: GuestMapper
): UnaryMapper<List<Guest>, List<GuestByTypeHolder>>() {

    override fun map(values: List<Guest>): List<GuestByTypeHolder> {
        var currentStatus: GuestStatusEnum? = null
        val guestsByType = arrayListOf<GuestByTypeHolder>()
        for(guest in values) {
            if((currentStatus == null) || (guest.status != currentStatus)) {
                currentStatus = guest.status
                guestsByType.add(
                    GuestByTypeHolder(guest.status.givenTitle, null)
                )
            }
            guestsByType.add(
                GuestByTypeHolder(null, guestMapper.map(guest))
            )
        }
        return guestsByType
    }

}