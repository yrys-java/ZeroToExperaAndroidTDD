package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.test_data

import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.state.UserDetailsUi
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.state.UserDetailsUiState

object UserDetailsTestUiData {

    val leanne = UserDetailsUi(
        id = 1,
        name = "Leanne Graham",
        username = "Bret",
        email = "Sincere@april.biz",
        phone = "1-770-736-8031 x56442",
        website = "hildegard.org",
        address = "Kulas Light, Apt. 556, Gwenborough, 92998-3874",
        company = "Romaguera-Crona"
    )

    val detailsContent = UserDetailsUiState.Content(
        user = leanne
    )
}