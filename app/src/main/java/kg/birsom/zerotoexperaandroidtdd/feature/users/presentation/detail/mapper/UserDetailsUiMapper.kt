package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.mapper

import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.User
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.state.UserDetailsUi

fun User.toDetailsUi(): UserDetailsUi = UserDetailsUi(
    id = id,
    name = name,
    username = username,
    email = email,
    phone = phone,
    website = website,
    address = "${address.street}, ${address.suite}, ${address.city}, ${address.zipcode}",
    company = company.name
)