package kg.birsom.zerotoexperaandroidtdd.core.network.exception

import java.io.IOException

sealed class ServerException(
    override val message: String = "Something went wrong. Please try again later"
) : IOException(message) {

    data object Unauthorized : ServerException("Unauthorized request")

    data object Forbidden : ServerException("Access is forbidden")

    data object NotFound : ServerException("Resource not found")

    data object TooManyRequests : ServerException("Too many requests. Please try again later")

    data object InternalServerError : ServerException("Internal server error")

    data object BadGateway : ServerException("Server is not available")

    data object ServiceUnavailable : ServerException("Server is temporarily unavailable")

    data object EmptyResponse : ServerException("Server returned empty response")

    data object Unknown : ServerException()
}