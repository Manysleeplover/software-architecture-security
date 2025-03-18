package ru.romanov.apoibas.lab3.model.entity

import jakarta.persistence.Embeddable

data class ExchangeRate(
    val country: String,
    val currency: String,
    val amount: Int,
    val code: String,
    val rate: Double,
){
    // Добавляем конструктор без аргументов
    constructor() : this("", "", 0, "", 0.0)
}