package kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model

sealed interface UserResult {

    data class Success(
        val user: User
    ) : UserResult

    data class Error(
        val error: UserError
    ) : UserResult
}

sealed interface UserError {

    data object NotFound : UserError
}