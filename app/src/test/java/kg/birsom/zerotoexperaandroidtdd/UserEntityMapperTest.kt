package kg.birsom.zerotoexperaandroidtdd

import kg.birsom.zerotoexperaandroidtdd.feature.users.data.local.entity.UserEntity
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.mapper.toDomain
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.mapper.toEntity
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.Address
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.Company
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.Geo
import kg.birsom.zerotoexperaandroidtdd.feature.users.domain.model.User
import org.junit.Assert.assertEquals
import org.junit.Test

class UserEntityMapperTest {

    @Test
    fun maps_domain_user_to_user_entity() {
        val user = User(
            id = 1,
            name = "Leanne Graham",
            username = "Bret",
            email = "Sincere@april.biz",
            address = Address(
                street = "Kulas Light",
                suite = "Apt. 556",
                city = "Gwenborough",
                zipcode = "92998-3874",
                geo = Geo(
                    lat = "-37.3159",
                    lng = "81.1496"
                )
            ),
            phone = "1-770-736-8031 x56442",
            website = "hildegard.org",
            company = Company(
                name = "Romaguera-Crona",
                catchPhrase = "Multi-layered client-server neural-net",
                bs = "harness real-time e-markets"
            )
        )

        val entity = user.toEntity()

        assertEquals(1, entity.id)
        assertEquals("Leanne Graham", entity.name)
        assertEquals("Bret", entity.username)
        assertEquals("Sincere@april.biz", entity.email)
        assertEquals("Kulas Light", entity.street)
        assertEquals("Apt. 556", entity.suite)
        assertEquals("Gwenborough", entity.city)
        assertEquals("92998-3874", entity.zipcode)
        assertEquals("-37.3159", entity.lat)
        assertEquals("81.1496", entity.lng)
        assertEquals("1-770-736-8031 x56442", entity.phone)
        assertEquals("hildegard.org", entity.website)
        assertEquals("Romaguera-Crona", entity.companyName)
        assertEquals("Multi-layered client-server neural-net", entity.companyCatchPhrase)
        assertEquals("harness real-time e-markets", entity.companyBs)
    }

    @Test
    fun maps_user_entity_to_domain_user() {
        val entity = UserEntity(
            id = 1,
            name = "Leanne Graham",
            username = "Bret",
            email = "Sincere@april.biz",
            street = "Kulas Light",
            suite = "Apt. 556",
            city = "Gwenborough",
            zipcode = "92998-3874",
            lat = "-37.3159",
            lng = "81.1496",
            phone = "1-770-736-8031 x56442",
            website = "hildegard.org",
            companyName = "Romaguera-Crona",
            companyCatchPhrase = "Multi-layered client-server neural-net",
            companyBs = "harness real-time e-markets"
        )

        val user = entity.toDomain()

        assertEquals(1, user.id)
        assertEquals("Leanne Graham", user.name)
        assertEquals("Bret", user.username)
        assertEquals("Sincere@april.biz", user.email)
        assertEquals("Kulas Light", user.address.street)
        assertEquals("Apt. 556", user.address.suite)
        assertEquals("Gwenborough", user.address.city)
        assertEquals("92998-3874", user.address.zipcode)
        assertEquals("-37.3159", user.address.geo.lat)
        assertEquals("81.1496", user.address.geo.lng)
        assertEquals("1-770-736-8031 x56442", user.phone)
        assertEquals("hildegard.org", user.website)
        assertEquals("Romaguera-Crona", user.company.name)
        assertEquals("Multi-layered client-server neural-net", user.company.catchPhrase)
        assertEquals("harness real-time e-markets", user.company.bs)
    }
}