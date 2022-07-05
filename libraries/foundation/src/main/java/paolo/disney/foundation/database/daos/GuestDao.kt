package paolo.disney.foundation.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import paolo.disney.foundation.database.dtos.GuestEntity


@Dao
abstract class GuestDao: BaseDao<GuestEntity> {

    @Query("SELECT * FROM guest ORDER BY status DESC")
    abstract fun findAllLive(): LiveData<List<GuestEntity>>

}