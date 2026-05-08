package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kg.birsom.zerotoexperaandroidtdd.R
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UsersError
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UsersResult
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.repository.UsersRepository
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.common.text.UiText
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.mapper.toUi
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.state.UserUi
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.state.UsersUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

private const val USERS_LOADED_KEY = "USERS_LOADED_KEY"

class UsersViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: UsersRepository
) : ViewModel() {

    private val mutableUiState = MutableStateFlow<UsersUiState>(UsersUiState.Loading)
    val uiState: StateFlow<UsersUiState> = mutableUiState.asStateFlow()

    suspend fun loadUsers() {
        updateState(repository.getUsers(forceUpdate = false))
        savedStateHandle[USERS_LOADED_KEY] = true
    }

    suspend fun refreshUsers() {
        val cachedUsers = mutableUiState.value.cachedUsers()

        mutableUiState.value = UsersUiState.Loading

        updateState(
            result = repository.getUsers(forceUpdate = true),
            cachedUsers = cachedUsers
        )
    }

    suspend fun restoreUsers() {
        val wasLoaded = savedStateHandle[USERS_LOADED_KEY] ?: false

        if (wasLoaded) {
            updateState(repository.getCachedUsers())
        }
    }

    fun restoreCachedUsersFromError() {
        val previousState = mutableUiState.value

        if (previousState is UsersUiState.Error && previousState.cachedUsers.isNotEmpty()) {
            mutableUiState.value = UsersUiState.Content(
                users = previousState.cachedUsers,
                offline = true
            )
        }
    }

    private fun updateState(
        result: UsersResult,
        cachedUsers: List<UserUi> = emptyList()
    ) {
        mutableUiState.value = when (result) {
            is UsersResult.Fresh -> UsersUiState.Content(
                users = result.users.map { it.toUi() },
                offline = false
            )

            is UsersResult.Cached -> UsersUiState.Content(
                users = result.users.map { it.toUi() },
                offline = true
            )

            is UsersResult.Error -> UsersUiState.Error(
                message = result.error.message(),
                cachedUsers = cachedUsers
            )
        }
    }

    private fun UsersUiState.cachedUsers(): List<UserUi> {
        return when (this) {
            is UsersUiState.Content -> users
            is UsersUiState.Error -> cachedUsers
            UsersUiState.Loading -> emptyList()
        }
    }

    private fun UsersError.message(): UiText {
        return when (this) {
            UsersError.NoInternet -> UiText.Res(R.string.users_error_no_internet)
            UsersError.Unauthorized -> UiText.Res(R.string.users_error_unauthorized)
            UsersError.Forbidden -> UiText.Res(R.string.users_error_forbidden)
            UsersError.NotFound -> UiText.Res(R.string.users_error_not_found)
            UsersError.ServerUnavailable -> UiText.Res(R.string.users_error_server_unavailable)
            UsersError.EmptyResponse -> UiText.Res(R.string.users_error_empty_response)
            UsersError.Unknown -> UiText.Res(R.string.users_error_unknown)
        }
    }
}