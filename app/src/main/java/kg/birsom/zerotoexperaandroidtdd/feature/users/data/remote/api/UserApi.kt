package kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.api

import kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.model.UserResponse
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}