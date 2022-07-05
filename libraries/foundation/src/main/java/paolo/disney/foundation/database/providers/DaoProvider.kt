package paolo.disney.foundation.database.providers

import paolo.disney.foundation.database.daos.GuestDao


class DaoProvider(private val database: DatabaseProvider) {

    fun getGuestDao(): GuestDao = database.getInstance().guestDao()

}