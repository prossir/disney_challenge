package paolo.disney.foundation.database.dtos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import paolo.disney.foundation.database.dtos.enums.GuestStatusEnum


@Entity(
    tableName = GuestEntity.TABLE_NAME,
    indices = [
        Index(value = [GuestEntity.FIELD_ID])
    ]
)
data class GuestEntity(
    @ColumnInfo(name = FIELD_ID)
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo(name = FIELD_NAME)
    val name: String,
    @ColumnInfo(name = FIELD_LASTNAME)
    val lastname: String,
    @ColumnInfo(name = FIELD_STATUS)
    val status: GuestStatusEnum
) {

    companion object {
        internal const val TABLE_NAME = "guest"

        internal const val FIELD_ID = "id"
        internal const val FIELD_NAME = "name"
        internal const val FIELD_LASTNAME = "lastname"
        internal const val FIELD_STATUS = "status"
    }

}