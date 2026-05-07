package kg.birsom.zerotoexperaandroidtdd.feature.users.data.repository

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

class UsersRepositoryImpl(
    private val api: UserApi,
    private val dao: UserDao,
    private val networkConnectivityService: NetworkConnectivityService? = null
) : UsersRepository {

    override suspend fun getUsers(): UsersResult {
        if (networkConnectivityService?.currentStatus() == NetworkStatus.Disconnected) {
            return getCachedUsers()
        }

        return try {
            val users = api.getUsers().map { it.toDomain() }
            dao.insertUsers(users.map { it.toEntity() })
            UsersResult.Fresh(users)
        } catch (_: Exception) {
            getCachedUsers()
        }
    }

    override suspend fun getUser(id: Int): UserResult {
        val user = dao.getUserById(id)?.toDomain()

        return if (user != null) {
            UserResult.Success(user)
        } else {
            UserResult.Error(UserError.NotFound)
        }
    }

    override suspend fun getCachedUsers(): UsersResult {
        val cachedUsers = dao.getUsers().map { it.toDomain() }

        return if (cachedUsers.isNotEmpty()) {
            UsersResult.Cached(cachedUsers)
        } else {
            UsersResult.Error(UsersError.NoInternet)
        }
    }
}