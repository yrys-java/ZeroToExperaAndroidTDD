package kg.birsom.zerotoexperaandroidtdd.feature.users.data.mapper

import kg.birsom.zerotoexperaandroidtdd.feature.users.data.local.entity.UserEntity
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.Address
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.Company
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.Geo
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.User

fun User.toEntity(): UserEntity = UserEntity(
    id = id,
    name = name,
    username = username,
    email = email,
    street = address.street,
    suite = address.suite,
    city = address.city,
    zipcode = address.zipcode,
    lat = address.geo.lat,
    lng = address.geo.lng,
    phone = phone,
    website = website,
    companyName = company.name,
    companyCatchPhrase = company.catchPhrase,
    companyBs = company.bs
)

fun UserEntity.toDomain(): User = User(
    id = id,
    name = name,
    username = username,
    email = email,
    address = Address(
        street = street,
        suite = suite,
        city = city,
        zipcode = zipcode,
        geo = Geo(
            lat = lat,
            lng = lng
        )
    ),
    phone = phone,
    website = website,
    company = Company(
        name = companyName,
        catchPhrase = companyCatchPhrase,
        bs = companyBs
    )
)