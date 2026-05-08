package kg.birsom.zerotoexperaandroidtdd

import kg.birsom.zerotoexperaandroidtdd.core.network.NetworkError
import kg.birsom.zerotoexperaandroidtdd.core.network.NetworkErrorHandler
import kg.birsom.zerotoexperaandroidtdd.core.network.exception.ServerException
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkErrorHandlerTest {

    @Test
    fun maps_server_exceptions_to_network_errors() {
        val scenarios = listOf(
            ServerException.Unauthorized to NetworkError.Unauthorized,
            ServerException.Forbidden to NetworkError.Forbidden,
            ServerException.NotFound to NetworkError.NotFound,
            ServerException.TooManyRequests to NetworkError.ServerUnavailable,
            ServerException.InternalServerError to NetworkError.ServerUnavailable,
            ServerException.BadGateway to NetworkError.ServerUnavailable,
            ServerException.ServiceUnavailable to NetworkError.ServerUnavailable,
            ServerException.EmptyResponse to NetworkError.EmptyResponse,
            ServerException.Unknown to NetworkError.Unknown
        )

        scenarios.forEach { (throwable, expectedError) ->
            assertEquals(expectedError, NetworkErrorHandler.handle(throwable))
        }
    }

    @Test
    fun maps_connectivity_exceptions_to_network_errors() {
        assertEquals(
            NetworkError.NoInternet,
            NetworkErrorHandler.handle(UnknownHostException())
        )
        assertEquals(
            NetworkError.ServerUnavailable,
            NetworkErrorHandler.handle(SocketTimeoutException())
        )
    }

    @Test
    fun maps_unexpected_exception_to_unknown_network_error() {
        assertEquals(
            NetworkError.Unknown,
            NetworkErrorHandler.handle(IllegalStateException("Broken response"))
        )
    }
}