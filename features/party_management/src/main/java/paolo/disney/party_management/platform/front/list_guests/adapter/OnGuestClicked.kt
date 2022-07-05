package paolo.disney.party_management.platform.front.list_guests.adapter

import paolo.disney.party_management.platform.front.common.dtos.GuestModel


interface OnGuestClicked {

    fun onGuestChecked(guest: GuestModel, isChecked: Boolean)

}