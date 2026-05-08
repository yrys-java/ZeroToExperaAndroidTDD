package kg.birsom.zerotoexperaandroidtdd.core.network

import kg.birsom.zerotoexperaandroidtdd.core.network.exception.ServerException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object NetworkErrorHandler {

    fun handle(throwable: Throwable): NetworkError {
        return when (throwable) {
            is UnknownHostException -> NetworkError.NoInternet
            is SocketTimeoutException -> NetworkError.ServerUnavailable
            ServerException.Unauthorized -> NetworkError.Unauthorized
            ServerException.Forbidden -> NetworkError.Forbidden
            ServerException.NotFound -> NetworkError.NotFound
            ServerException.TooManyRequests,
            ServerException.InternalServerError,
            ServerException.BadGateway,
            ServerException.ServiceUnavailable -> NetworkError.ServerUnavailable
            ServerException.EmptyResponse -> NetworkError.EmptyResponse
            ServerException.Unknown -> NetworkError.Unknown
            else -> NetworkError.Unknown
        }
    }
}