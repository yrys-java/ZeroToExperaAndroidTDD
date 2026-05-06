package kg.birsom.zerotoexperaandroidtdd.feature.users.data.local.entity

data class UserEntity(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val lat: String,
    val lng: String,
    val phone: String,
    val website: String,
    val companyName: String,
    val companyCatchPhrase: String,
    val companyBs: String
)