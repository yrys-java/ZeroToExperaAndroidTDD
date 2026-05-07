package kg.birsom.zerotoexperaandroidtdd.core.network

import kg.birsom.zerotoexperaandroidtdd.core.network.exception.ServerException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

private const val DEFAULT_ERROR_MESSAGE = "Something went wrong. Please try again later"

fun Throwable.getErrorMessage(): String {
    return when (this) {
        is UnknownHostException -> "No internet connection"
        is SocketTimeoutException -> "Connection timeout. Please try again"
        is ServerException -> message ?: DEFAULT_ERROR_MESSAGE
        else -> message ?: DEFAULT_ERROR_MESSAGE
    }
}