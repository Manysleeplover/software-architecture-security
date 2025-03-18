package ru.romanov.apoibas.lab3.util

import ru.romanov.apoibas.lab3.model.dto.DailyExchangeRateDTO
import ru.romanov.apoibas.lab3.model.dto.ExchangeRateDTO
import ru.romanov.apoibas.lab3.model.entity.DailyExchangeRateEntity
import ru.romanov.apoibas.lab3.model.entity.ExchangeRate
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun formatDate(date: LocalDate): String {
   return date.format(
        DateTimeFormatter.ofPattern("dd.MM.yyyy")
    )
}


fun List<DailyExchangeRateDTO>.toEntity(): List<DailyExchangeRateEntity> =
    this.map { it.toEntity() }

fun DailyExchangeRateDTO.toEntity(): DailyExchangeRateEntity =
    DailyExchangeRateEntity(
        date = this.date,
        rates = this.rates.etoEntity()
    )

fun List<ExchangeRateDTO>.etoEntity(): List<ExchangeRate> =
    this.map {
        ExchangeRate(
            country = it.country,
            currency = it.currency,
            amount = it.amount,
            code = it.code,
            rate = it.rate
        )
    }