package ru.romanov.apoibas.lab3.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.romanov.apoibas.lab3.model.entity.DailyExchangeRateEntity
import java.time.LocalDate


interface DailyExchangeRateRepository : JpaRepository<DailyExchangeRateEntity, LocalDate>{
}