package kg.birsom.zerotoexperaandroidtdd.core.network.interceptor

import kg.birsom.zerotoexperaandroidtdd.core.network.exception.ServerException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.HttpURLConnection.HTTP_BAD_GATEWAY
import java.net.HttpURLConnection.HTTP_FORBIDDEN
import java.net.HttpURLConnection.HTTP_INTERNAL_ERROR
import java.net.HttpURLConnection.HTTP_NO_CONTENT
import java.net.HttpURLConnection.HTTP_NOT_FOUND
import java.net.HttpURLConnection.HTTP_RESET
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED
import java.net.HttpURLConnection.HTTP_UNAVAILABLE

class ServerErrorInterceptor(
    private val emptyBodyIsError: Boolean = true
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val serverException = response.toServerException()

        if (serverException != null) {
            response.close()
            throw serverException
        }

        if (emptyBodyIsError && response.hasEmptyBody()) {
            response.close()
            throw ServerException.EmptyResponse
        }

        return response
    }

    private fun Response.toServerException(): ServerException? {
        return when (code) {
            HTTP_UNAUTHORIZED -> ServerException.Unauthorized
            HTTP_FORBIDDEN -> ServerException.Forbidden
            HTTP_NOT_FOUND -> ServerException.NotFound
            HTTP_TOO_MANY_REQUESTS -> ServerException.TooManyRequests
            HTTP_INTERNAL_ERROR -> ServerException.InternalServerError
            HTTP_BAD_GATEWAY -> ServerException.BadGateway
            HTTP_UNAVAILABLE -> ServerException.ServiceUnavailable
            else -> if (isSuccessful) null else ServerException.Unknown
        }
    }

    private fun Response.hasEmptyBody(): Boolean {
        if (code == HTTP_NO_CONTENT || code == HTTP_RESET) {
            return false
        }

        val responseBody = body ?: return true

        if (responseBody.contentLength() == 0L) {
            return true
        }

        return peekBody(MAX_PEEK_BODY_BYTES).string().isBlank()
    }

    private companion object {
        const val HTTP_TOO_MANY_REQUESTS = 429
        const val MAX_PEEK_BODY_BYTES = 1024L
    }
}