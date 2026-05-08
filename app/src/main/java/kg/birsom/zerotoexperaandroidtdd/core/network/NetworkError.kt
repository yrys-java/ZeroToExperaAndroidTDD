package kg.birsom.zerotoexperaandroidtdd.core.network

sealed interface NetworkError {

    data object NoInternet : NetworkError

    data object Unauthorized : NetworkError

    data object Forbidden : NetworkError

    data object NotFound : NetworkError

    data object ServerUnavailable : NetworkError

    data object EmptyResponse : NetworkError

    data object Unknown : NetworkError
}