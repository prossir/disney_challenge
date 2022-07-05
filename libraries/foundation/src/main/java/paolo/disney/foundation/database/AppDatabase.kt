package paolo.disney.foundation.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import paolo.disney.foundation.database.converters.GuestStatusConverter
import paolo.disney.foundation.database.daos.GuestDao
import paolo.disney.foundation.database.dtos.GuestEntity


@Database(
    entities = [
        GuestEntity::class
    ],
    version = AppDatabase.VERSION,
    exportSchema = false
)
@TypeConverters(
    GuestStatusConverter::class
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun guestDao(): GuestDao

    companion object {
        const val VERSION = 1
        const val NAME = "disney_challenge_db"
    }

}