package kg.birsom.zerotoexperaandroidtdd.feature.users.data.repository

import kg.birsom.zerotoexperaandroidtdd.feature.users.data.local.dao.UserDao
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.mapper.toDomain
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.mapper.toEntity
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.api.UserApi
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.User
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.repository.UsersRepository

class UsersRepositoryImpl(
    private val api: UserApi,
    private val dao: UserDao
) : UsersRepository {

    override suspend fun getUsers(): List<User> {
        return try {
            val users = api.getUsers().map { it.toDomain() }
            dao.insertUsers(users.map { it.toEntity() })
            users
        } catch (_: Exception) {
            dao.getUsers().map { it.toDomain() }
        }
    }
}