package ru.romanov.apoibas.lab3.model.dto

import java.time.LocalDate

data class ReportRequestDTO(
    val startDate: LocalDate,
    val endDate: LocalDate,
    val code: String
)
