package kg.birsom.zerotoexperaandroidtdd

import androidx.lifecycle.SavedStateHandle
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.Address
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.Company
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.Geo
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.User
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UserError
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UserResult
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.UsersResult
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.repository.UsersRepository
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.state.UserDetailsUi
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.state.UserDetailsUiState
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.viewmodel.UserDetailsViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class UserDetailsViewModelTest {

    @Test
    fun loads_user_details_by_id_from_saved_state_handle() = runBlocking {
        val savedStateHandle = SavedStateHandle(
            mapOf("userId" to 2)
        )
        val repository = FakeUsersRepository(
            userResult = UserResult.Success(
                user = user(id = 2, name = "Ervin Howell")
            )
        )
        val viewModel = UserDetailsViewModel(
            savedStateHandle = savedStateHandle,
            repository = repository
        )

        viewModel.loadUser()

        assertEquals(2, repository.requestedUserId)
        assertEquals(
            UserDetailsUiState.Content(
                user = UserDetailsUi(
                    id = 2,
                    name = "Ervin Howell",
                    username = "username2",
                    email = "user2@example.com",
                    phone = "phone2",
                    website = "website2",
                    address = "street2, suite2, city2, zipcode2",
                    company = "company2"
                )
            ),
            viewModel.uiState.value
        )
    }

    @Test
    fun shows_not_found_error_when_user_does_not_exist() = runBlocking {
        val savedStateHandle = SavedStateHandle(
            mapOf("userId" to 404)
        )
        val repository = FakeUsersRepository(
            userResult = UserResult.Error(UserError.NotFound)
        )
        val viewModel = UserDetailsViewModel(
            savedStateHandle = savedStateHandle,
            repository = repository
        )

        viewModel.loadUser()

        assertEquals(
            UserDetailsUiState.Error(message = "User not found"),
            viewModel.uiState.value
        )
    }

    @Test
    fun restores_user_id_after_process_recreation() = runBlocking {
        val savedStateHandle = SavedStateHandle(
            mapOf("userId" to 2)
        )
        val firstRepository = FakeUsersRepository(
            userResult = UserResult.Success(
                user = user(id = 2, name = "Ervin Howell")
            )
        )
        val firstViewModel = UserDetailsViewModel(
            savedStateHandle = savedStateHandle,
            repository = firstRepository
        )

        firstViewModel.loadUser()

        val secondRepository = FakeUsersRepository(
            userResult = UserResult.Success(
                user = user(id = 2, name = "Ervin Howell")
            )
        )
        val recreatedViewModel = UserDetailsViewModel(
            savedStateHandle = savedStateHandle,
            repository = secondRepository
        )

        recreatedViewModel.loadUser()

        assertEquals(2, secondRepository.requestedUserId)
    }

    private class FakeUsersRepository(
        private val userResult: UserResult
    ) : UsersRepository {

        var requestedUserId: Int? = null

        override suspend fun getUsers(): UsersResult {
            throw IllegalStateException("Not used in this test")
        }

        override suspend fun getCachedUsers(): UsersResult {
            throw IllegalStateException("Not used in this test")
        }

        override suspend fun getUser(id: Int): UserResult {
            requestedUserId = id
            return userResult
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