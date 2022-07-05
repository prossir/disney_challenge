package paolo.disney.party_management.platform.front.common.mappers

import androidx.lifecycle.MutableLiveData
import paolo.disney.foundation.constants.FoundationConstants
import paolo.disney.foundation.mappers.Mapper
import paolo.disney.party_management.domain.entities.Guest
import paolo.disney.party_management.platform.front.common.dtos.GuestModel


class GuestMapper: Mapper<Guest, GuestModel>() {

    override fun reverseMap(value: GuestModel) = Guest(
        id = value.id,
        name = value.name.value ?: FoundationConstants.EMPTY_STRING,
        lastname = value.lastname.value ?: FoundationConstants.EMPTY_STRING,
        status = value.status
    )

    override fun map(value: Guest) = GuestModel(
        id = value.id,
        name = MutableLiveData(value.name),
        lastname = MutableLiveData(value.lastname),
        status = value.status
    )

}