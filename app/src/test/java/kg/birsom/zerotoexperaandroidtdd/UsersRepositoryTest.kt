package kg.birsom.zerotoexperaandroidtdd

import kg.birsom.zerotoexperaandroidtdd.core.network.exception.ServerException
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.local.dao.UserDao
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.local.entity.UserEntity
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.api.UserApi
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.model.AddressResponse
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.model.CompanyResponse
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.model.GeoResponse
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.model.UserResponse
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.repository.UsersRepositoryImpl
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UserError
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UserResult
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UsersError
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UsersResult
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.repository.UsersRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class UsersRepositoryTest {

    @Test
    fun loads_users_from_api_saves_cache_and_returns_fresh_users() = runBlocking {
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

        val result = repository.getUsers() as UsersResult.Fresh
        val users = result.users

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
        val scenarios = listOf(
            UnknownHostException(),
            ServerException.Unauthorized,
            ServerException.Forbidden,
            ServerException.NotFound,
            ServerException.TooManyRequests,
            ServerException.InternalServerError,
            ServerException.BadGateway,
            ServerException.ServiceUnavailable,
            ServerException.EmptyResponse,
            ServerException.Unknown
        )

        scenarios.forEach { exception ->
            val api = FakeUserApi(error = exception)
            val dao = FakeUserDao(
                cachedUsers = listOf(
                    userEntity(id = 1, name = "Cached Leanne"),
                    userEntity(id = 2, name = "Cached Ervin")
                )
            )
            val repository: UsersRepository = UsersRepositoryImpl(
                api = api,
                dao = dao
            )

            val result = repository.getUsers() as UsersResult.Cached

            assertEquals(1, api.getUsersCalledCount)
            assertEquals(2, result.users.size)
            assertEquals("Cached Leanne", result.users[0].name)
            assertEquals("Cached Ervin", result.users[1].name)
        }
    }
    @Test
    fun returns_error_when_api_fails_and_cache_is_empty() = runBlocking {
        val api = FakeUserApi(
            error = UnknownHostException("No internet")
        )
        val dao = FakeUserDao()
        val repository: UsersRepository = UsersRepositoryImpl(
            api = api,
            dao = dao
        )

        val result = repository.getUsers()

        assertEquals(
            UsersResult.Error(UsersError.NoInternet),
            result
        )
    }

    @Test
    fun returns_unknown_when_api_fails_with_unexpected_error_and_cache_is_empty() = runBlocking {
        val api = FakeUserApi(
            error = IllegalStateException("Broken response")
        )
        val dao = FakeUserDao()
        val repository: UsersRepository = UsersRepositoryImpl(
            api = api,
            dao = dao
        )

        val result = repository.getUsers()

        assertEquals(
            UsersResult.Error(UsersError.Unknown),
            result
        )
    }

    @Test
    fun returns_user_by_id_from_cache() = runBlocking {
        val api = FakeUserApi()
        val dao = FakeUserDao(
            cachedUsers = listOf(
                userEntity(id = 1, name = "Leanne Graham"),
                userEntity(id = 2, name = "Ervin Howell")
            )
        )
        val repository: UsersRepository = UsersRepositoryImpl(
            api = api,
            dao = dao
        )

        val result = repository.getUser(id = 2) as UserResult.Success

        assertEquals("Ervin Howell", result.user.name)
    }

    @Test
    fun returns_not_found_when_user_does_not_exist_in_cache() = runBlocking {
        val api = FakeUserApi()
        val dao = FakeUserDao()
        val repository: UsersRepository = UsersRepositoryImpl(
            api = api,
            dao = dao
        )

        val result = repository.getUser(id = 404)

        assertEquals(
            UserResult.Error(UserError.NotFound),
            result
        )
    }

    @Test
    fun returns_cached_users_without_api_call() = runBlocking {
        val api = FakeUserApi(
            users = listOf(
                userResponse(id = 9, name = "Remote User")
            )
        )
        val dao = FakeUserDao(
            cachedUsers = listOf(
                userEntity(id = 1, name = "Cached Leanne"),
                userEntity(id = 2, name = "Cached Ervin")
            )
        )
        val repository: UsersRepository = UsersRepositoryImpl(
            api = api,
            dao = dao
        )

        val result = repository.getCachedUsers() as UsersResult.Cached
        val users = result.users

        assertEquals(0, api.getUsersCalledCount)
        assertEquals(2, users.size)
        assertEquals("Cached Leanne", users[0].name)
        assertEquals("Cached Ervin", users[1].name)
    }

    @Test
    fun returns_no_internet_when_api_fails_without_connection_and_cache_is_empty() = runBlocking {
        val api = FakeUserApi(
            error = UnknownHostException()
        )
        val dao = FakeUserDao()
        val repository: UsersRepository = UsersRepositoryImpl(
            api = api,
            dao = dao
        )

        val result = repository.getUsers()

        assertEquals(
            UsersResult.Error(UsersError.NoInternet),
            result
        )
    }

    @Test
    fun returns_mapped_error_when_api_fails_and_cache_is_empty() = runBlocking {
        val scenarios = listOf(
            ServerException.Unauthorized to UsersError.Unauthorized,
            ServerException.Forbidden to UsersError.Forbidden,
            ServerException.NotFound to UsersError.NotFound,
            ServerException.TooManyRequests to UsersError.ServerUnavailable,
            ServerException.InternalServerError to UsersError.ServerUnavailable,
            ServerException.BadGateway to UsersError.ServerUnavailable,
            ServerException.ServiceUnavailable to UsersError.ServerUnavailable,
            ServerException.EmptyResponse to UsersError.EmptyResponse,
            ServerException.Unknown to UsersError.Unknown
        )

        scenarios.forEach { (exception, expectedError) ->
            val api = FakeUserApi(error = exception)
            val dao = FakeUserDao()
            val repository: UsersRepository = UsersRepositoryImpl(
                api = api,
                dao = dao
            )

            val result = repository.getUsers()

            assertEquals(
                UsersResult.Error(expectedError),
                result
            )
        }
    }

    private class FakeUserApi(
        private val users: List<UserResponse> = emptyList(),
        private val error: Exception? = null
    ) : UserApi {

        var getUsersCalledCount = 0

        override suspend fun getUsers(): List<UserResponse> {
            getUsersCalledCount++
            error?.let { throw it }
            return users
        }
    }

    private class FakeUserDao(
        cachedUsers: List<UserEntity> = emptyList()
    ) : UserDao {

        val users = cachedUsers.toMutableList()

        override suspend fun getUsers(): List<UserEntity> {
            return users
        }

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