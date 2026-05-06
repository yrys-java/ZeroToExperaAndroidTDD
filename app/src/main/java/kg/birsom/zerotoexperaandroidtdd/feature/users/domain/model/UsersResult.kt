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
}