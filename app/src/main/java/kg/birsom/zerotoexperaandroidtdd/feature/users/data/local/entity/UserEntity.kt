package kg.birsom.zerotoexperaandroidtdd.feature.users.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "street")
    val street: String,
    @ColumnInfo(name = "suite")
    val suite: String,
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "zipcode")
    val zipcode: String,
    @ColumnInfo(name = "lat")
    val lat: String,
    @ColumnInfo(name = "lng")
    val lng: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "website")
    val website: String,
    @ColumnInfo(name = "companyName")
    val companyName: String,
    @ColumnInfo(name = "companyCatchPhrase")
    val companyCatchPhrase: String,
    @ColumnInfo(name = "companyBs")
    val companyBs: String
)