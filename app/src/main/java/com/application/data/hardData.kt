package com.application.data

import com.application.models.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random


private val photos = listOf(
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQNBL4N8DMZ7RSIQRojA6ZjR0GHAhvTV3AR9S9Erk6r2YjZtG7EZhgf-WLjUlXMQTqhafk&usqp=CAU",
    "https://png.pngtree.com/png-vector/20190710/ourlarge/pngtree-user-vector-avatar-png-image_1541962.jpg",
    "https://i.pinimg.com/originals/d5/b0/4c/d5b04cc3dcd8c17702549ebc5f1acf1a.png",
    "https://i.pinimg.com/originals/ba/d4/5a/bad45a40fa6e153ef8d1599ba875102c.png",
    "https://cdn-icons-png.flaticon.com/512/3231/3231671.png",
    "https://icons-for-free.com/iconfiles/png/512/boy+man+person+user+woman+icon-1320085967769585303.png",
    "https://cdn-icons-png.flaticon.com/512/3048/3048127.png",
    "https://img.icons8.com/color/480/person-male.png"
)

private val names = listOf(
    "Doe John",
    "Phillips Mark",
    "Phillips Warren",
    "Williams Robert",
    "Amira Miller",
    "Reece Figueroa",
    "Nehemiah Barrera",
    "Audrey Roach",
    "Kael Strickland",
    "Janiah Willis",
    "Lindsay Phelps",
    "Amaya Hoover",
    "Marquise Hood",
    "Davin Herrera",
    "Gerald Duran",
    "Sarah Madden"
)
private val careers = listOf(
    "Architecture and engineering",
    "Arts, culture and entertainment",
    "Business, management and administration",
    "Communications",
    "Community and social services",
    "Education",
    "Science and technology",
    "Installation, repair and maintenance"
)

private val usersFlow = MutableStateFlow(
    List(50) { index -> randomUser(id = index + 1) }
)


private fun randomUser(id: Int): UserModel = UserModel(
    id = id,
    photo = photos[Random.nextInt(photos.size)],
    username = names[Random.nextInt(names.size)],
    career = careers[Random.nextInt(careers.size)],
    email = "",
    phone = "",
    homeAddress = "",
    dataBirth = ""
)

fun getHardUsersList(): StateFlow<List<UserModel>> {
    return usersFlow
}
