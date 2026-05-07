package kg.birsom.zerotoexperaandroidtdd.app

import kg.birsom.zerotoexperaandroidtdd.R
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.common.text.UiText
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.state.UserDetailsUi
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.state.UserDetailsUiState
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.state.UserUi
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.state.UsersUiState

object UsersAppDemoData {

    private val users = listOf(
        UserDetailsUi(
            id = 1,
            name = "Leanne Graham",
            username = "Bret",
            email = "Sincere@april.biz",
            phone = "1-770-736-8031 x56442",
            website = "hildegard.org",
            address = "Kulas Light, Apt. 556, Gwenborough, 92998-3874",
            company = "Romaguera-Crona"
        ),
        UserDetailsUi(
            id = 2,
            name = "Ervin Howell",
            username = "Antonette",
            email = "Shanna@melissa.tv",
            phone = "010-692-6593 x09125",
            website = "anastasia.net",
            address = "Victor Plains, Suite 879, Wisokyburgh, 90566-7771",
            company = "Deckow-Crist"
        ),
        UserDetailsUi(
            id = 3,
            name = "Clementine Bauch",
            username = "Samantha",
            email = "Nathan@yesenia.net",
            phone = "1-463-123-4447",
            website = "ramiro.info",
            address = "Douglas Extension, Suite 847, McKenziehaven, 59590-4157",
            company = "Romaguera-Jacobson"
        )
    )

    val usersContent = UsersUiState.Content(
        users = users.map { user ->
            UserUi(
                id = user.id,
                name = user.name,
                email = user.email
            )
        },
        offline = false
    )

    fun detailsState(userId: Int): UserDetailsUiState {
        val user = users.firstOrNull { it.id == userId }

        return if (user != null) {
            UserDetailsUiState.Content(user = user)
        } else {
            UserDetailsUiState.Error(
                message = UiText.Res(R.string.user_details_error_not_found)
            )
        }
    }
}