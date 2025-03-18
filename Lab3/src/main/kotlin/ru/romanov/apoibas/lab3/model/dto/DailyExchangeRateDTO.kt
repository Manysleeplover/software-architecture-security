package ru.romanov.apoibas.lab3.model.dto

import com.opencsv.bean.CsvBindByName
import java.time.LocalDate

data class DailyExchangeRateDTO(
    val date: LocalDate,
    val rates: List<ExchangeRateDTO>
)

data class ExchangeRateDTO(
    @CsvBindByName(column = "country")
    val country: String = "",

    @CsvBindByName(column = "currency")
    val currency: String = "",

    @CsvBindByName(column = "amount")
    val amount: Int = 0,

    @CsvBindByName(column = "code")
    val code: String = "",

    @CsvBindByName(column = "rate")
    val rate: Double = 0.0
){
    // Добавляем конструктор без аргументов
    constructor() : this("", "", 0, "", 0.0)
}