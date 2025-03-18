package ru.romanov.apoibas.lab3.config


import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "scheduler.exchange-rate")
data class SchedulerConfig(
    private val cron: String,
)