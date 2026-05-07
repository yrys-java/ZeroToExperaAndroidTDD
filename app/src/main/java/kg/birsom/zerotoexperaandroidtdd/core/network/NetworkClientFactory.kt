package kg.birsom.zerotoexperaandroidtdd.core.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kg.birsom.zerotoexperaandroidtdd.core.network.environment.Environment
import kg.birsom.zerotoexperaandroidtdd.core.network.interceptor.ServerErrorInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkClientFactory {

    private const val DEFAULT_CONNECT_TIMEOUT_SECONDS = 60L
    private const val DEFAULT_READ_TIMEOUT_SECONDS = 60L
    private const val DEFAULT_WRITE_TIMEOUT_SECONDS = 60L

    fun createGson(): Gson {
        return GsonBuilder().create()
    }

    fun createOkHttpClient(
        serverErrorInterceptor: Interceptor = ServerErrorInterceptor(),
        loggingEnabled: Boolean = false
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(DEFAULT_CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(serverErrorInterceptor)

        if (loggingEnabled) {
            builder.addInterceptor(createLoggingInterceptor())
        }

        return builder.build()
    }

    fun createRetrofit(
        environment: Environment = Environment.Default,
        okHttpClient: OkHttpClient = createOkHttpClient(),
        gson: Gson = createGson()
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(environment.restAddress)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun createLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}