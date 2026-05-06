package kg.birsom.zerotoexperaandroidtdd

import kg.birsom.zerotoexperaandroidtdd.feature.users.data.mapper.toDomain
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.model.AddressResponse
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.model.CompanyResponse
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.model.GeoResponse
import kg.birsom.zerotoexperaandroidtdd.feature.users.data.remote.model.UserResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class UserResponseMapperTest {

    @Test
    fun maps_user_response_to_domain_user() {
        val response = UserResponse(
            id = 1,
            name = "Leanne Graham",
            username = "Bret",
            email = "Sincere@april.biz",
            address = AddressResponse(
                street = "Kulas Light",
                suite = "Apt. 556",
                city = "Gwenborough",
                zipcode = "92998-3874",
                geo = GeoResponse(
                    lat = "-37.3159",
                    lng = "81.1496"
                )
            ),
            phone = "1-770-736-8031 x56442",
            website = "hildegard.org",
            company = CompanyResponse(
                name = "Romaguera-Crona",
                catchPhrase = "Multi-layered client-server neural-net",
                bs = "harness real-time e-markets"
            )
        )

        val user = response.toDomain()

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