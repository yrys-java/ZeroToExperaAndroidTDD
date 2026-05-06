package kg.birsom.zerotoexperaandroidtdd.feature.users.domain.repository

import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UsersResult

interface UsersRepository {

    suspend fun getUsers(): UsersResult
}