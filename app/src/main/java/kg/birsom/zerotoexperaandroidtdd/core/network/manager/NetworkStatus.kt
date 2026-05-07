package kg.birsom.zerotoexperaandroidtdd.core.network.manager

sealed interface NetworkStatus {

    data object Unknown : NetworkStatus

    data object Connected : NetworkStatus

    data object Disconnected : NetworkStatus
}