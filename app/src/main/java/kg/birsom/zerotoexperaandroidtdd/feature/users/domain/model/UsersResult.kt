package kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model

sealed interface UsersResult {

    data class Fresh(
        val users: List<User>
    ) : UsersResult

    data class Cached(
        val users: List<User>
    ) : UsersResult

    data class Error(
        val error: UsersError
    ) : UsersResult
}

sealed interface UsersError {

    data object NoInternet : UsersError

    data object Unauthorized : UsersError

    data object Forbidden : UsersError

    data object NotFound : UsersError

    data object ServerUnavailable : UsersError

    data object EmptyResponse : UsersError

    data object Unknown : UsersError
}