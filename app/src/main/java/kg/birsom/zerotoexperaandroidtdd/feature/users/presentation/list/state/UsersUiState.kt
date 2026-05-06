package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.state

sealed interface UsersUiState {

    data object Loading : UsersUiState

    data class Content(
        val users: List<UserUi>,
        val offline: Boolean
    ) : UsersUiState

    data class Error(
        val message: String
    ) : UsersUiState
}