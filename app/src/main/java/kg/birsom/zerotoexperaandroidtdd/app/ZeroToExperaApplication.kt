package kg.birsom.zerotoexperaandroidtdd.app

import android.app.Application
import kg.birsom.zerotoexperaandroidtdd.app.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ZeroToExperaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ZeroToExperaApplication)
            modules(appModules)
        }
    }
}