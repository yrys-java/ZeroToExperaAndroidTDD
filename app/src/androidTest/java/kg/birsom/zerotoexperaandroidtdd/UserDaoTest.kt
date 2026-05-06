package kg.birsom.zerotoexperaandroidtdd

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.local.UserDatabase
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.local.dao.UserDao
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.local.entity.UserEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserDaoTest {

    private lateinit var database: UserDatabase
    private lateinit var dao: UserDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            UserDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = database.userDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun returns_empty_list_when_cache_is_empty() = runBlocking {
        assertEquals(emptyList<UserEntity>(), dao.getUsers())
    }

    @Test
    fun inserts_and_reads_users() = runBlocking {
        val users = listOf(
            userEntity(id = 1, name = "Leanne Graham"),
            userEntity(id = 2, name = "Ervin Howell")
        )

        dao.insertUsers(users)

        assertEquals(users, dao.getUsers())
    }

    @Test
    fun replaces_user_with_same_id() = runBlocking {
        dao.insertUsers(
            listOf(
                userEntity(id = 1, name = "Old Name")
            )
        )

        val updated = userEntity(id = 1, name = "New Name")
        dao.insertUsers(listOf(updated))

        assertEquals(listOf(updated), dao.getUsers())
    }

    @Test
    fun returns_user_by_id() = runBlocking {
        val leanne = userEntity(id = 1, name = "Leanne Graham")
        val ervin = userEntity(id = 2, name = "Ervin Howell")
        dao.insertUsers(listOf(leanne, ervin))

        assertEquals(ervin, dao.getUserById(id = 2))
    }

    @Test
    fun returns_null_when_user_does_not_exist() = runBlocking {
        assertEquals(null, dao.getUserById(id = 404))
    }

    private fun userEntity(
        id: Int,
        name: String
    ) = UserEntity(
        id = id,
        name = name,
        username = "username$id",
        email = "user$id@example.com",
        street = "street$id",
        suite = "suite$id",
        city = "city$id",
        zipcode = "zipcode$id",
        lat = "lat$id",
        lng = "lng$id",
        phone = "phone$id",
        website = "website$id",
        companyName = "company$id",
        companyCatchPhrase = "catchPhrase$id",
        companyBs = "bs$id"
    )
}