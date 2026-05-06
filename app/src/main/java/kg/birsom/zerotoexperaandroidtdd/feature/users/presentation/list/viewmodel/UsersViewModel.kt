package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.viewmodel

import androidx.lifecycle.ViewModel
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UsersError
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UsersResult
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.repository.UsersRepository
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.mapper.toUi
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.state.UsersUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UsersViewModel(
    private val repository: UsersRepository
) : ViewModel() {

    private val mutableUiState = MutableStateFlow<UsersUiState>(UsersUiState.Loading)
    val uiState: StateFlow<UsersUiState> = mutableUiState.asStateFlow()

    suspend fun loadUsers() {
        mutableUiState.value = when (val result = repository.getUsers()) {
            is UsersResult.Fresh -> UsersUiState.Content(
                users = result.users.map { it.toUi() },
                offline = false
            )

            is UsersResult.Cached -> UsersUiState.Content(
                users = result.users.map { it.toUi() },
                offline = true
            )

            is UsersResult.Error -> UsersUiState.Error(
                message = result.error.message()
            )
        }
    }

    suspend fun retry() {
        loadUsers()
    }

    private fun UsersError.message(): String {
        return when (this) {
            UsersError.NoInternet -> "No internet connection"
        }
    }
}