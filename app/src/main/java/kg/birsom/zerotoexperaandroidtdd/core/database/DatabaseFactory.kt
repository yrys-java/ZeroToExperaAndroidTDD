package kg.birsom.zerotoexperaandroidtdd.core.database

import android.content.Context
import androidx.room.Room

internal class DatabaseFactory(
    private val context: Context
) {

    fun create(): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    private companion object {

        const val DATABASE_NAME = "users.db"
    }
}