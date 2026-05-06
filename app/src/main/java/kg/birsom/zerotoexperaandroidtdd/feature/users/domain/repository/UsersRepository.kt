package kg.birsom.zerotoexperaandroidtdd.feature.users.domain.repository

import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UserResult
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UsersResult

interface UsersRepository {

    suspend fun getUsers(): UsersResult

    suspend fun getUser(id: Int): UserResult

    suspend fun getCachedUsers(): UsersResult
}