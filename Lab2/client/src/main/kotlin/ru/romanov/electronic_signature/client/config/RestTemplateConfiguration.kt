package ru.romanov.electronic_signature.client.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class RestTemplateConfiguration {

    @Bean
    fun serverRestClient(): RestClient {
        return RestClient.create("http://localhost:8080")
    }

}