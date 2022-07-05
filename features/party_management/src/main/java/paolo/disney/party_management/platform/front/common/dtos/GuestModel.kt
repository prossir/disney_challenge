package paolo.disney.party_management.platform.front.common.dtos

import android.widget.AdapterView
import androidx.lifecycle.MutableLiveData
import paolo.disney.foundation.constants.FoundationConstants
import paolo.disney.foundation.database.dtos.enums.GuestStatusEnum
import paolo.disney.party_management.platform.front.common.validators.GuestValidator


data class GuestModel(
    var id: Long,
    var name: MutableLiveData<String>,
    var lastname: MutableLiveData<String>,
    var status: GuestStatusEnum
) {
    var validator: GuestValidator = GuestValidator()

    init {
        validator.setForObservation(name, lastname)
    }

    val fullName: String
        get() = "${name.value} ${lastname.value}"

    val hasAReservation: Boolean
        get() = status == GuestStatusEnum.HAS_RESERVATION

    private val hasAReservationDescription: String
        get() = if(hasAReservation) "has a reservation" else "does not have a reservation"

    val fullDescription: String
        get() = "$fullName $hasAReservationDescription and can be selected for forming a party"

    val checkedStatusDescription: String
        get() = "$fullName is ${if(!isChecked) " not " else ""} selected to form a party"

    var isChecked: Boolean = false

    /**
     * Listener for changing the status of a guest on creation. Will not work otherwise
     * */
    val onStatusChangedListener = AdapterView.OnItemClickListener { _, _, position, _ ->
        status = GuestStatusEnum.fromSelectorPosition(position)
    }

    fun reset() {
        id = FoundationConstants.EMPTY_LONG
        name.value = FoundationConstants.EMPTY_STRING
        lastname.value = FoundationConstants.EMPTY_STRING
        status = GuestStatusEnum.HAS_RESERVATION
    }

    companion object {
        fun new() = GuestModel(
            id = FoundationConstants.EMPTY_LONG,
            name = MutableLiveData(FoundationConstants.EMPTY_STRING),
            lastname = MutableLiveData(FoundationConstants.EMPTY_STRING),
            status = GuestStatusEnum.HAS_RESERVATION
        )
    }

}