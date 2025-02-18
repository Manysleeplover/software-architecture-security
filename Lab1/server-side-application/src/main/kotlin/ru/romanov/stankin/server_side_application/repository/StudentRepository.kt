package ru.romanov.stankin.server_side_application.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.romanov.stankin.server_side_application.entity.Student

@Repository
interface StudentRepository : JpaRepository<Student, Long> {
}