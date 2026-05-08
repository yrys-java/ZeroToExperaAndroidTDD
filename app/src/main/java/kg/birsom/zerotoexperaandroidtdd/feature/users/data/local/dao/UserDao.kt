package kg.birsom.zerotoexperaandroidtdd.feature.users.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.local.entity.UserEntity

@Dao
abstract class UserDao {

    @Query("SELECT * FROM users ORDER BY id ASC")
    abstract suspend fun getUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertUsers(users: List<UserEntity>)

    @Query("DELETE FROM users")
    abstract suspend fun deleteUsers()

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    abstract suspend fun getUserById(id: Int): UserEntity?

    @Transaction
    open suspend fun replaceUsers(users: List<UserEntity>) {
        deleteUsers()
        insertUsers(users)
    }
}