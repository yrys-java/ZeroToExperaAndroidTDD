package kg.birsom.zerotoexperaandroidtdd.feature.users.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.local.dao.UserDao
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}