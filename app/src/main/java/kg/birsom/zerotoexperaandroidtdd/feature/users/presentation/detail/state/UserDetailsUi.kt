package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.state

data class UserDetailsUi(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String,
    val address: String,
    val company: String
)