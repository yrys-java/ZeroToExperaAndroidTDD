package kg.birsom.zerotoexperaandroidtdd.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.local.dao.UserDao
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}