package ru.romanov.apoibas.lab3.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfiguration {

    @Bean
    fun cnbRestClient(): RestClient {
        return RestClient.create("https://www.cnb.cz/en/financial_markets/foreign_exchange_market/exchange_rate_fixing/")
    }

}