package paolo.disney.party_management.domain.entities

import paolo.disney.foundation.database.dtos.enums.GuestStatusEnum


data class Guest(
    val id: Long,
    val name: String,
    val lastname: String,
    val status: GuestStatusEnum
)