package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kg.birsom.zerotoexperaandroidtdd.R
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UserError
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UserResult
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.repository.UsersRepository
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.common.text.UiText
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.mapper.toDetailsUi
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.state.UserDetailsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

private const val USER_ID_KEY = "userId"

class UserDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: UsersRepository
) : ViewModel() {

    private val mutableUiState = MutableStateFlow<UserDetailsUiState>(UserDetailsUiState.Loading)
    val uiState: StateFlow<UserDetailsUiState> = mutableUiState.asStateFlow()

    suspend fun loadUser() {
        val userId = savedStateHandle.get<Int>(USER_ID_KEY) ?: return showNotFound()

        loadUser(userId)
    }

    suspend fun loadUser(userId: Int) {
        mutableUiState.value = when (val result = repository.getUser(userId)) {
            is UserResult.Success -> UserDetailsUiState.Content(
                user = result.user.toDetailsUi()
            )

            is UserResult.Error -> UserDetailsUiState.Error(
                message = result.error.message()
            )
        }
    }

    private fun showNotFound() {
        mutableUiState.value = UserDetailsUiState.Error(
            message = UserError.NotFound.message()
        )
    }

    private fun UserError.message(): UiText {
        return when (this) {
            UserError.NotFound -> UiText.Res(R.string.user_details_error_not_found)
        }
    }
}
