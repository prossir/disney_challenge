package paolo.disney.foundation.database.converters

import androidx.room.TypeConverter
import paolo.disney.foundation.database.dtos.enums.GuestStatusEnum


class GuestStatusConverter {

    @TypeConverter
    fun fromGuestStatus(guestStatus: GuestStatusEnum): String {
        return guestStatus.name
    }

    @TypeConverter
    fun toGuestStatus(guestStatus: String): GuestStatusEnum {
        return GuestStatusEnum.valueOf(guestStatus)
    }

}