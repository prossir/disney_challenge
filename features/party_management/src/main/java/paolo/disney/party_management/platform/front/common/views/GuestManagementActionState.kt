package paolo.disney.party_management.platform.front.common.views

import paolo.disney.foundation.events.Event


sealed class GuestManagementActionState {
    // Guest creation related
    object GuestCreationDismissed: GuestManagementActionState()
    object GuestCreated: GuestManagementActionState()
    data class GuestCreationFailure(val message: String): GuestManagementActionState()

    // Party creation related
    data class NavigateToConfirmParty(val event: Event<Long>): GuestManagementActionState()
    data class NavigateToResolveConflictAboutParty(val event: Event<Long>): GuestManagementActionState()
    object EventInvalidPartyRegistration: GuestManagementActionState()

}