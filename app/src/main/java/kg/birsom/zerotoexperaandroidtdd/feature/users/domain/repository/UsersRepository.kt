package kg.birsom.zerotoexperaandroidtdd.feature.users.domain.repository

import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.User

interface UsersRepository {

    suspend fun getUsers(): List<User>
}