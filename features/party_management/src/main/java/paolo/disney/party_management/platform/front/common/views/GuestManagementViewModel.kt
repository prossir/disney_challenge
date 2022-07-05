package paolo.disney.party_management.platform.front.common.views

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import paolo.disney.foundation.events.Event
import paolo.disney.foundation.extensions.safeLaunch
import paolo.disney.foundation.extensions.withDispatcher
import paolo.disney.party_management.domain.use_cases.GetGuestsUseCase
import paolo.disney.party_management.domain.use_cases.RegisterGuestUseCase
import paolo.disney.party_management.platform.front.common.dtos.GuestModel
import paolo.disney.party_management.platform.front.common.dtos.PartyModel
import paolo.disney.party_management.platform.front.common.mappers.GuestByTypeHolderMapper
import paolo.disney.party_management.platform.front.common.mappers.GuestMapper
import paolo.disney.party_management.platform.front.list_guests.adapter.GuestByTypeHolder
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class GuestManagementViewModel @Inject constructor(
    //Use cases
    private val getGuestsUseCase: GetGuestsUseCase,
    private val registerGuestUseCase: RegisterGuestUseCase,
    //Mappers
    private val guestMapper: GuestMapper,
    private val guestByTypeHolderMapper: GuestByTypeHolderMapper,
    //Dispatchers
    private val dispatcher: CoroutineDispatcher
): ViewModel() {

    private val _actionState = MutableLiveData<GuestManagementActionState>()
    val actionState: LiveData<GuestManagementActionState>
        get() = _actionState

    val areGuestByTypeLoaded: MutableLiveData<Boolean> = MutableLiveData(false)
    lateinit var guestsByType: LiveData<List<GuestByTypeHolder>>

    fun getGuests() {
        viewModelScope.safeLaunch(::getGuestsExceptionHandler) {
            withDispatcher(dispatcher) {
                guestsByType = Transformations.map(getGuestsUseCase()) {
                    guestByTypeHolderMapper.map(it)
                }
                areGuestByTypeLoaded.postValue(true)
            }
        }
    }
    private fun getGuestsExceptionHandler(t: Throwable) {
        Timber.e(t)
    }

    /**
     * New guest logic.
     */
    val newGuest: LiveData<GuestModel> = MutableLiveData(GuestModel.new())
    fun initNewGuest() {
        newGuest.value?.apply {
            this.reset()
        }
    }

    fun dismissGuestCreation() {
        _actionState.postValue(GuestManagementActionState.GuestCreationDismissed)
    }

    fun registerNewGuest() {
        viewModelScope.safeLaunch(::registerNewGuestExceptionHandler) {
            withDispatcher(dispatcher) {
                newGuest.value?.apply {
                    registerGuestUseCase(
                        guestMapper.reverseMap(this)
                    )
                    dismissGuestCreation()
                }
            }
        }
    }
    private fun registerNewGuestExceptionHandler(t: Throwable) {
        Timber.e(t)
    }

    /**
     * Party confirmation logic.
     * */
    val party = PartyModel.new()

    fun onGuestChecked(guest: GuestModel) {
        party.registerOrUnregisterGuest(guest)
    }

    fun registerPartyOfGuests() {
        if(party.validator.hasAtLeastOneGuestWithReservations.value == true) {
            if(party.validator.allGuestHaveReservations.value == true) {
                _actionState.postValue(GuestManagementActionState.NavigateToConfirmParty(
                    Event(System.currentTimeMillis()))
                )
            }
            else {
                _actionState.postValue(GuestManagementActionState.NavigateToResolveConflictAboutParty(
                    Event(System.currentTimeMillis()))
                )
            }
        }
        else {
            _actionState.postValue(GuestManagementActionState.EventInvalidPartyRegistration)
        }
    }

    fun resetParty() {
        party.reset()
    }

}