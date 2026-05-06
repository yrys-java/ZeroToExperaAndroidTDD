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
        val response = api.getUsers().map { it.toDomain() }
        dao.insertUsers(response.map { it.toEntity() })
        return response
    }
}