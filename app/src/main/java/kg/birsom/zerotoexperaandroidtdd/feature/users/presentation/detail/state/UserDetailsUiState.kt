package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.state

sealed interface UserDetailsUiState {

    data object Loading : UserDetailsUiState

    data class Content(
        val user: UserDetailsUi
    ) : UserDetailsUiState

    data class Error(
        val message: String
    ) : UserDetailsUiState
}