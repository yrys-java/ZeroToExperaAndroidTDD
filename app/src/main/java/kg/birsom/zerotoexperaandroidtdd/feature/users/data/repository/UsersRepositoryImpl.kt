package kg.birsom.zerotoexperaandroidtdd.feature.users.data.repository

import kg.birsom.zerotoexperaandroidtdd.core.network.NetworkError
import kg.birsom.zerotoexperaandroidtdd.core.network.NetworkErrorHandler
import kg.birsom.zerotoexperaandroidtdd.core.network.manager.NetworkConnectivityService
import kg.birsom.zerotoexperaandroidtdd.core.network.manager.NetworkStatus
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.local.dao.UserDao
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.mapper.toDomain
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.mapper.toEntity
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.api.UserApi
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UserError
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UserResult
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UsersError
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UsersResult
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.repository.UsersRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersRepositoryImpl(
    private val api: UserApi,
    private val dao: UserDao,
    private val networkConnectivityService: NetworkConnectivityService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UsersRepository {

    override suspend fun getUsers(forceUpdate: Boolean): UsersResult = withContext(ioDispatcher) {
        if (networkConnectivityService.currentStatus() == NetworkStatus.Disconnected) {
            if (forceUpdate) {
                UsersResult.Error(UsersError.NoInternet)
            } else {
                getCachedUsers(fallbackError = UsersError.NoInternet)
            }
        } else {
            try {
                loadFreshUsers()
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                val error = NetworkErrorHandler.handle(exception).toUsersError()

                if (forceUpdate) {
                    UsersResult.Error(error)
                } else {
                    getCachedUsers(fallbackError = error)
                }
            }
        }
    }

    private suspend fun loadFreshUsers(): UsersResult.Fresh {
        val users = api.getUsers().map { it.toDomain() }
        dao.replaceUsers(users.map { it.toEntity() })
        return UsersResult.Fresh(users)
    }

    override suspend fun getUser(id: Int): UserResult = withContext(ioDispatcher) {
        val user = dao.getUserById(id)?.toDomain()

        if (user != null) {
            UserResult.Success(user)
        } else {
            UserResult.Error(UserError.NotFound)
        }
    }

    override suspend fun getCachedUsers(): UsersResult = withContext(ioDispatcher) {
        getCachedUsers(fallbackError = UsersError.NoInternet)
    }

    private suspend fun getCachedUsers(fallbackError: UsersError): UsersResult {
        val cachedUsers = dao.getUsers().map { it.toDomain() }

        return if (cachedUsers.isNotEmpty()) {
            UsersResult.Cached(cachedUsers)
        } else {
            UsersResult.Error(fallbackError)
        }
    }

    private fun NetworkError.toUsersError(): UsersError {
        return when (this) {
            NetworkError.NoInternet -> UsersError.NoInternet
            NetworkError.Unauthorized -> UsersError.Unauthorized
            NetworkError.Forbidden -> UsersError.Forbidden
            NetworkError.NotFound -> UsersError.NotFound
            NetworkError.ServerUnavailable -> UsersError.ServerUnavailable
            NetworkError.EmptyResponse -> UsersError.EmptyResponse
            NetworkError.Unknown -> UsersError.Unknown
        }
    }
}