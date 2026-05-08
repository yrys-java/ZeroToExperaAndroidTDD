package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.test_data

import kg.birsom.zerotoexperaandroidtdd.R
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.common.text.UiText
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.state.UserUi
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.state.UsersUiState

object TestUiData {

    val users = listOf(
        UserUi(
            id = 1,
            name = "Leanne Graham",
            email = "Sincere@april.biz"
        ),
        UserUi(
            id = 2,
            name = "Ervin Howell",
            email = "Shanna@melissa.tv"
        ),
        UserUi(
            id = 3,
            name = "Clementine Bauch",
            email = "Nathan@yesenia.net"
        )
    )

    val usersContent = UsersUiState.Content(
        users = users,
        offline = false
    )

    val usersOfflineContent = UsersUiState.Content(
        users = users,
        offline = true
    )

    val usersError = UsersUiState.Error(
        message = UiText.Res(R.string.users_error_no_internet)
    )

    val usersErrorWithCache = UsersUiState.Error(
        message = UiText.Res(R.string.users_error_forbidden),
        cachedUsers = users
    )
}