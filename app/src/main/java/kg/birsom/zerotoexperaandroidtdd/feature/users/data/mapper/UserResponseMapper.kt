package kg.birsom.zerotoexperaandroidtdd.feature.users.data.mapper

import kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.model.UserResponse
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.model.AddressResponse
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.model.CompanyResponse
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.model.GeoResponse
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.Address
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.Company
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.Geo
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.User

fun UserResponse.toDomain(): User = User(
    id = id,
    name = name,
    username = username,
    email = email,
    address = address.toDomain(),
    phone = phone,
    website = website,
    company = company.toDomain()
)

private fun AddressResponse.toDomain(): Address = Address(
    street = street,
    suite = suite,
    city = city,
    zipcode = zipcode,
    geo = geo.toDomain()
)

private fun GeoResponse.toDomain(): Geo = Geo(
    lat = lat,
    lng = lng
)

private fun CompanyResponse.toDomain(): Company = Company(
    name = name,
    catchPhrase = catchPhrase,
    bs = bs
)