package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.state

import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.common.text.UiText

sealed interface UserDetailsUiState {

    data object Loading : UserDetailsUiState

    data class Content(
        val user: UserDetailsUi
    ) : UserDetailsUiState

    data class Error(
        val message: UiText
    ) : UserDetailsUiState
}