package kg.birsom.zerotoexperaandroidtdd.core.network

import kg.birsom.zerotoexperaandroidtdd.core.network.exception.ServerException
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UsersError
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object NetworkErrorHandler {

    fun handle(throwable: Throwable): UsersError {
        return when (throwable) {
            is UnknownHostException -> UsersError.NoInternet
            is SocketTimeoutException -> UsersError.ServerUnavailable
            ServerException.Unauthorized -> UsersError.Unauthorized
            ServerException.Forbidden -> UsersError.Forbidden
            ServerException.NotFound -> UsersError.NotFound
            ServerException.TooManyRequests,
            ServerException.InternalServerError,
            ServerException.BadGateway,
            ServerException.ServiceUnavailable -> UsersError.ServerUnavailable
            ServerException.EmptyResponse -> UsersError.EmptyResponse
            ServerException.Unknown -> UsersError.Unknown
            else -> UsersError.Unknown
        }
    }
}