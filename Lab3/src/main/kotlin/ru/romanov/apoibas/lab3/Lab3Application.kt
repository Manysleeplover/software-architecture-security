package ru.romanov.apoibas.lab3

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling


@ConfigurationPropertiesScan
@EnableConfigurationProperties
@SpringBootApplication
@EnableScheduling
class Lab3Application

fun main(args: Array<String>) {
	runApplication<Lab3Application>(*args)
}
