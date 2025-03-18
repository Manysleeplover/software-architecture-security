package ru.romanov.apoibas.lab3.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.LocalDate

@Entity
@Table(name = "exchange_rate")
data class DailyExchangeRateEntity(
    @Id
    val date: LocalDate,

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    val rates: List<ExchangeRate>
){
    // Добавляем конструктор без аргументов
    constructor() : this(LocalDate.now(), emptyList())
}