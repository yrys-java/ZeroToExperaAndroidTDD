package kg.birsom.zerotoexperaandroidtdd.app.di

import kg.birsom.zerotoexperaandroidtdd.core.database.AppDatabase
import kg.birsom.zerotoexperaandroidtdd.core.database.DatabaseFactory
import kg.birsom.zerotoexperaandroidtdd.core.network.NetworkClientFactory
import kg.birsom.zerotoexperaandroidtdd.core.network.environment.Environment
import kg.birsom.zerotoexperaandroidtdd.core.network.manager.NetworkConnectivityService
import kg.birsom.zerotoexperaandroidtdd.core.network.manager.NetworkConnectivityServiceImpl
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.api.UserApi
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.repository.UsersRepositoryImpl
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.repository.UsersRepository
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.viewmodel.UserDetailsViewModel
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.viewmodel.UsersViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit

private val coreModule = module {
    single<Environment> { Environment.Default }
    single { NetworkClientFactory.createGson() }
    single<OkHttpClient> {
        NetworkClientFactory.createOkHttpClient(
            loggingEnabled = true
        )
    }
    single<Retrofit> {
        NetworkClientFactory.createRetrofit(
            environment = get(),
            okHttpClient = get(),
            gson = get()
        )
    }
    single<NetworkConnectivityService> {
        NetworkConnectivityServiceImpl(context = androidContext())
    }
    single<AppDatabase> {
        DatabaseFactory(context = androidContext()).create()
    }
}

private val usersDataModule = module {
    single { get<AppDatabase>().userDao() }
    single<UserApi> { get<Retrofit>().create(UserApi::class.java) }
    single<UsersRepository> {
        UsersRepositoryImpl(
            api = get(),
            dao = get(),
            networkConnectivityService = get()
        )
    }
}

private val usersPresentationModule = module {
    viewModelOf(::UsersViewModel)
    viewModelOf(::UserDetailsViewModel)
}

val appModules = listOf(
    coreModule,
    usersDataModule,
    usersPresentationModule
)
