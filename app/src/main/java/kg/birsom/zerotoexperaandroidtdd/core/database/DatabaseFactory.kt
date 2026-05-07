package kg.birsom.zerotoexperaandroidtdd.core.database

import android.content.Context
import androidx.room.Room
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.local.UserDatabase

object DatabaseFactory {

    private const val DATABASE_NAME = "users.db"

    fun createUserDatabase(context: Context): UserDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            UserDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}