package paolo.disney.foundation.database.dtos.enums


enum class GuestStatusEnum {
    HAS_RESERVATION,
    DOES_NOT_HAVE_RESERVATION;

    val givenTitle: String
        get() {
            return when(this) {
                HAS_RESERVATION -> {
                    "These Guest Have Reservations"
                }
                DOES_NOT_HAVE_RESERVATION -> {
                    "These Guests Need Reservations"
                }
            }
        }

    companion object {

        fun fromSelectorPosition(position: Int): GuestStatusEnum {
            return when(position) {
                0 -> {
                    HAS_RESERVATION
                }
                else -> {
                    DOES_NOT_HAVE_RESERVATION
                }
            }
        }

    }

}