package paolo.disney.party_management.platform.front.common.validators

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import paolo.disney.foundation.constants.FoundationConstants
import paolo.disney.foundation.extensions.isAboveExpectedLength


class GuestValidator {
    // The sources of this have to be implemented on the model
    val hasValidName: MediatorLiveData<Boolean> = MediatorLiveData()
    val invalidNameMessage: MediatorLiveData<String> = MediatorLiveData()

    val hasValidLastname: MediatorLiveData<Boolean> = MediatorLiveData()
    val invalidLastnameMessage: MediatorLiveData<String> = MediatorLiveData()

    // this are dependant on the other two sources
    val isValid: MediatorLiveData<Boolean> = MediatorLiveData<Boolean>()

    init {
        isValid.addSource(hasValidName) {
            isValid.value = it && (hasValidLastname.value == true)
        }

        isValid.addSource(hasValidLastname) {
            isValid.value = (hasValidName.value == true) && it
        }
    }

    fun setForObservation(name: MutableLiveData<String>, lastname: MutableLiveData<String>) {
        hasValidName.addSource(name) {
            hasValidName.value = it.isNotBlank() && it.isNotEmpty() &&
                    !it.isAboveExpectedLength(MAXIMUM_NUMBER_OF_CHARACTERS)
        }

        invalidNameMessage.addSource(name) {
            invalidNameMessage.value = if(it.isBlank() || it.isEmpty()) {
                "Name cannot be blank"
            }
            else if(it.isAboveExpectedLength(MAXIMUM_NUMBER_OF_CHARACTERS)) {
                "Name cannot exceed $MAXIMUM_NUMBER_OF_CHARACTERS characters"
            }
            else {
                FoundationConstants.EMPTY_STRING
            }
        }

        hasValidLastname.addSource(lastname) {
            hasValidLastname.value = it.isNotBlank() && it.isNotEmpty() &&
                    !it.isAboveExpectedLength(MAXIMUM_NUMBER_OF_CHARACTERS)
        }

        invalidLastnameMessage.addSource(lastname) {
            invalidLastnameMessage.value = if(it.isBlank() || it.isEmpty()) {
                "Lastname cannot be blank"
            }
            else if(it.isAboveExpectedLength(MAXIMUM_NUMBER_OF_CHARACTERS)) {
                "Lastname cannot exceed $MAXIMUM_NUMBER_OF_CHARACTERS characters"
            }
            else {
                FoundationConstants.EMPTY_STRING
            }
        }
    }

    companion object {
        const val MAXIMUM_NUMBER_OF_CHARACTERS = 30
    }

}