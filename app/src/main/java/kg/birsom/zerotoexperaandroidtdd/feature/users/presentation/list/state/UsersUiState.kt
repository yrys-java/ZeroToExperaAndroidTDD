package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.state

import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.common.text.UiText

sealed interface UsersUiState {

    data object Loading : UsersUiState

    data class Content(
        val users: List<UserUi>,
        val offline: Boolean
    ) : UsersUiState

    data class Error(
        val message: UiText,
        val cachedUsers: List<UserUi> = emptyList()
    ) : UsersUiState
}