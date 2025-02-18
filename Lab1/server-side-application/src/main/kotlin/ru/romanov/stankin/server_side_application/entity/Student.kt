package ru.romanov.stankin.server_side_application.entity

import jakarta.persistence.*

/*
{
    "name": "Иван",
    "surname": "Иванов",
    "patronym": "Иванлыич",
    "stgroup": "ИДБ-00-00",
    "cardid": "1234567"
}
 */

@Entity
class Student (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val cardId: Long?,
    val name: String,
    val surname: String,
    val patronymic: String,
    @Column(name = "student_group")
    val studentGroup: String,
)