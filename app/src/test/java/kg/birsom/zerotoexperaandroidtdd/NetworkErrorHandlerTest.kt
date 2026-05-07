package kg.birsom.zerotoexperaandroidtdd

import kg.birsom.zerotoexperaandroidtdd.core.network.NetworkErrorHandler
import kg.birsom.zerotoexperaandroidtdd.core.network.exception.ServerException
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UsersError
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkErrorHandlerTest {

    @Test
    fun maps_server_exceptions_to_users_errors() {
        val scenarios = listOf(
            ServerException.Unauthorized to UsersError.Unauthorized,
            ServerException.Forbidden to UsersError.Forbidden,
            ServerException.NotFound to UsersError.NotFound,
            ServerException.TooManyRequests to UsersError.ServerUnavailable,
            ServerException.InternalServerError to UsersError.ServerUnavailable,
            ServerException.BadGateway to UsersError.ServerUnavailable,
            ServerException.ServiceUnavailable to UsersError.ServerUnavailable,
            ServerException.EmptyResponse to UsersError.EmptyResponse,
            ServerException.Unknown to UsersError.Unknown
        )

        scenarios.forEach { (throwable, expectedError) ->
            assertEquals(expectedError, NetworkErrorHandler.handle(throwable))
        }
    }

    @Test
    fun maps_connectivity_exceptions_to_users_errors() {
        assertEquals(
            UsersError.NoInternet,
            NetworkErrorHandler.handle(UnknownHostException())
        )
        assertEquals(
            UsersError.ServerUnavailable,
            NetworkErrorHandler.handle(SocketTimeoutException())
        )
    }

    @Test
    fun maps_unexpected_exception_to_unknown_users_error() {
        assertEquals(
            UsersError.Unknown,
            NetworkErrorHandler.handle(IllegalStateException("Broken response"))
        )
    }
}