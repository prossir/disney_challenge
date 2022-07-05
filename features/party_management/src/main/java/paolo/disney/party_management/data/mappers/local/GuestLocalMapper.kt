package paolo.disney.party_management.data.mappers.local

import paolo.disney.foundation.database.dtos.GuestEntity
import paolo.disney.foundation.mappers.Mapper
import paolo.disney.party_management.domain.entities.Guest


class GuestLocalMapper: Mapper<GuestEntity, Guest>() {

    override fun reverseMap(value: Guest) = GuestEntity(
        id = value.id,
        name = value.name,
        lastname = value.lastname,
        status = value.status
    )

    override fun map(value: GuestEntity) = Guest(
        id = value.id,
        name = value.name,
        lastname = value.lastname,
        status = value.status
    )

}