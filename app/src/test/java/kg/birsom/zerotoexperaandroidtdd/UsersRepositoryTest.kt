package kg.birsom.zerotoexperaandroidtdd

import kg.birsom.zerotoexperaandroidtdd.feature.users.data.local.dao.UserDao
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.local.entity.UserEntity
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.api.UserApi
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.model.AddressResponse
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.model.CompanyResponse
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.model.GeoResponse
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.model.UserResponse
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.repository.UsersRepositoryImpl
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.repository.UsersRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class UsersRepositoryTest {

    @Test
    fun loads_users_from_api_saves_cache_and_returns_domain_users() = runBlocking {
        val api = FakeUserApi(
            users = listOf(
                userResponse(id = 1, name = "Leanne Graham"),
                userResponse(id = 2, name = "Ervin Howell")
            )
        )
        val dao = FakeUserDao()
        val repository: UsersRepository = UsersRepositoryImpl(
            api = api,
            dao = dao
        )

        val users = repository.getUsers()

        assertEquals(2, users.size)
        assertEquals("Leanne Graham", users[0].name)
        assertEquals("Ervin Howell", users[1].name)
        assertEquals(
            listOf(
                userEntity(id = 1, name = "Leanne Graham"),
                userEntity(id = 2, name = "Ervin Howell")
            ),
            dao.users
        )
    }

    @Test
    fun returns_cached_users_when_api_fails_and_cache_exists() = runBlocking {
        val api = FakeUserApi(
            error = IllegalStateException("No internet")
        )
        val dao = FakeUserDao(
            cachedUsers = mutableListOf(
                userEntity(id = 1, name = "Cached Leanne"),
                userEntity(id = 2, name = "Cached Ervin")
            )
        )
        val repository: UsersRepository = UsersRepositoryImpl(
            api = api,
            dao = dao
        )

        val users = repository.getUsers()

        assertEquals(2, users.size)
        assertEquals("Cached Leanne", users[0].name)
        assertEquals("Cached Ervin", users[1].name)
    }

    @Test
    fun returns_cached_users_when_api_fails_and_empty_cache() = runBlocking {
        val api = FakeUserApi(
            error = IllegalStateException("No internet")
        )
        val dao = FakeUserDao()
        val repository: UsersRepository = UsersRepositoryImpl(
            api = api,
            dao = dao
        )

        val users = repository.getUsers()

        assertEquals(0, users.size)
    }

    private class FakeUserApi(
        private val users: List<UserResponse> = emptyList(),
        private val error: Exception? = null
    ) : UserApi {

        override suspend fun getUsers(): List<UserResponse> {
            error?.let { throw it }
            return users
        }
    }

    private class FakeUserDao(
        cachedUsers: MutableList<UserEntity> = mutableListOf()
    ) : UserDao {
        val users = cachedUsers.toMutableList()

        override suspend fun getUsers(): List<UserEntity> = users

        override suspend fun insertUsers(users: List<UserEntity>) {
            this.users.clear()
            this.users.addAll(users)
        }

        override suspend fun getUserById(id: Int): UserEntity? {
            return users.firstOrNull { it.id == id }
        }
    }
    private fun userResponse(
        id: Int,
        name: String
    ) = UserResponse(
        id = id,
        name = name,
        username = "username$id",
        email = "user$id@example.com",
        address = AddressResponse(
            street = "street$id",
            suite = "suite$id",
            city = "city$id",
            zipcode = "zipcode$id",
            geo = GeoResponse(
                lat = "lat$id",
                lng = "lng$id"
            )
        ),
        phone = "phone$id",
        website = "website$id",
        company = CompanyResponse(
            name = "company$id",
            catchPhrase = "catchPhrase$id",
            bs = "bs$id"
        )
    )

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