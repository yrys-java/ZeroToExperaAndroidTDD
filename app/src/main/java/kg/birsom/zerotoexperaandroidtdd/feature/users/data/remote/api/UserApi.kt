package kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.api

import kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.model.UserResponse

interface UserApi {

    suspend fun getUsers(): List<UserResponse>
}