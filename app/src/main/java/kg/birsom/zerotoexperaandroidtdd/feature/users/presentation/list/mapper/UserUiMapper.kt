package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.mapper

import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.User
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.state.UserUi

fun User.toUi(): UserUi = UserUi(
    id = id,
    name = name,
    email = email
)