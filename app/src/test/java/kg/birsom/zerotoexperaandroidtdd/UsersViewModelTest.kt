package kg.birsom.zerotoexperaandroidtdd

import androidx.lifecycle.SavedStateHandle
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.Address
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.Company
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.Geo
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.User
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UserResult
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UsersError
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UsersResult
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.repository.UsersRepository
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.common.text.UiText
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.state.UserUi
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.state.UsersUiState
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.viewmodel.UsersViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class UsersViewModelTest {

    @Test
    fun shows_fresh_users_content_when_repository_returns_fresh_users() = runBlocking {
        val repository = FakeUsersRepository(
            usersResult = UsersResult.Fresh(
                users = listOf(
                    user(id = 1, name = "Leanne Graham"),
                    user(id = 2, name = "Ervin Howell")
                )
            )
        )
        val viewModel = UsersViewModel(
            savedStateHandle = SavedStateHandle(),
            repository = repository
        )

        viewModel.loadUsers()

        assertEquals(
            UsersUiState.Content(
                users = listOf(
                    UserUi(id = 1, name = "Leanne Graham", email = "user1@example.com"),
                    UserUi(id = 2, name = "Ervin Howell", email = "user2@example.com")
                ),
                offline = false
            ),
            viewModel.uiState.value
        )
    }

    @Test
    fun shows_cached_users_content_when_repository_returns_cached_users() = runBlocking {
        val repository = FakeUsersRepository(
            usersResult = UsersResult.Cached(
                users = listOf(
                    user(id = 1, name = "Cached Leanne")
                )
            )
        )
        val viewModel = UsersViewModel(
            savedStateHandle = SavedStateHandle(),
            repository = repository
        )

        viewModel.loadUsers()

        assertEquals(
            UsersUiState.Content(
                users = listOf(
                    UserUi(id = 1, name = "Cached Leanne", email = "user1@example.com")
                ),
                offline = true
            ),
            viewModel.uiState.value
        )
    }

    @Test
    fun shows_no_internet_error_when_repository_returns_error() = runBlocking {
        val repository = FakeUsersRepository(
            usersResult = UsersResult.Error(UsersError.NoInternet)
        )

        val viewModel = UsersViewModel(
            savedStateHandle = SavedStateHandle(),
            repository = repository
        )

        viewModel.loadUsers()

        assertEquals(
            UsersUiState.Error(message = UiText.Res(R.string.users_error_no_internet)),
            viewModel.uiState.value
        )
    }

    @Test
    fun retry_loads_users_again_after_error() = runBlocking {
        val repository = FakeUsersRepository(
            usersResults = mutableListOf(
                UsersResult.Error(UsersError.NoInternet),
                UsersResult.Fresh(
                    users = listOf(
                        user(id = 1, name = "Leanne Graham")
                    )
                )
            )
        )
        val viewModel = UsersViewModel(
            savedStateHandle = SavedStateHandle(),
            repository = repository
        )

        viewModel.loadUsers()
        assertEquals(
            UsersUiState.Error(message = UiText.Res(R.string.users_error_no_internet)),
            viewModel.uiState.value
        )

        viewModel.retry()

        assertEquals(
            UsersUiState.Content(
                users = listOf(
                    UserUi(id = 1, name = "Leanne Graham", email = "user1@example.com")
                ),
                offline = false
            ),
            viewModel.uiState.value
        )
        assertEquals(2, repository.getUsersCalledCount)
    }

    @Test
    fun restores_users_from_cache_after_process_recreation() = runBlocking {
        val savedStateHandle = SavedStateHandle()
        val firstRepository = FakeUsersRepository(
            usersResult = UsersResult.Fresh(
                users = listOf(
                    user(id = 1, name = "Leanne Graham")
                )
            )
        )
        var viewModel = UsersViewModel(
            savedStateHandle = savedStateHandle,
            repository = firstRepository
        )

        viewModel.loadUsers()

        val secondRepository = FakeUsersRepository(
            cachedUsersResult = UsersResult.Cached(
                users = listOf(
                    user(id = 1, name = "Cached Leanne")
                )
            )
        )
        viewModel = UsersViewModel(
            savedStateHandle = savedStateHandle,
            repository = secondRepository
        )

        viewModel.restoreUsers()

        assertEquals(0, secondRepository.getUsersCalledCount)
        assertEquals(1, secondRepository.getCachedUsersCalledCount)
        assertEquals(
            UsersUiState.Content(
                users = listOf(
                    UserUi(id = 1, name = "Cached Leanne", email = "user1@example.com")
                ),
                offline = true
            ),
            viewModel.uiState.value
        )
    }

    @Test
    fun shows_error_when_restored_cache_is_empty() = runBlocking {
        val savedStateHandle = SavedStateHandle()
        val firstRepository = FakeUsersRepository(
            usersResult = UsersResult.Fresh(
                users = listOf(
                    user(id = 1, name = "Leanne Graham")
                )
            )
        )
        var viewModel = UsersViewModel(
            savedStateHandle = savedStateHandle,
            repository = firstRepository
        )

        viewModel.loadUsers()

        val secondRepository = FakeUsersRepository(
            cachedUsersResult = UsersResult.Error(UsersError.NoInternet)
        )
        viewModel = UsersViewModel(
            savedStateHandle = savedStateHandle,
            repository = secondRepository
        )

        viewModel.restoreUsers()

        assertEquals(
            UsersUiState.Error(message = UiText.Res(R.string.users_error_no_internet)),
            viewModel.uiState.value
        )
    }

    private class FakeUsersRepository(
        usersResult: UsersResult = UsersResult.Error(UsersError.NoInternet),
        private val cachedUsersResult: UsersResult = UsersResult.Error(UsersError.NoInternet),
        private val usersResults: MutableList<UsersResult> = mutableListOf(usersResult)
    ) : UsersRepository {

        var getUsersCalledCount = 0
        var getCachedUsersCalledCount = 0

        override suspend fun getUsers(): UsersResult {
            getUsersCalledCount++
            return usersResults.removeFirst()
        }

        override suspend fun getCachedUsers(): UsersResult {
            getCachedUsersCalledCount++
            return cachedUsersResult
        }

        override suspend fun getUser(id: Int): UserResult {
            throw IllegalStateException("Not used in this test")
        }
    }

    private fun user(
        id: Int,
        name: String
    ) = User(
        id = id,
        name = name,
        username = "username$id",
        email = "user$id@example.com",
        address = Address(
            street = "street$id",
            suite = "suite$id",
            city = "city$id",
            zipcode = "zipcode$id",
            geo = Geo(
                lat = "lat$id",
                lng = "lng$id"
            )
        ),
        phone = "phone$id",
        website = "website$id",
        company = Company(
            name = "company$id",
            catchPhrase = "catchPhrase$id",
            bs = "bs$id"
        )
    )
}