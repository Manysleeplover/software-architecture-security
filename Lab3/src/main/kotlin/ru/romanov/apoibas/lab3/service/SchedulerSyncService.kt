package ru.romanov.apoibas.lab3.service

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDate


@Service
class SchedulerSyncService(
    private val exchangeRateService: ExchangeRateService,

    ) {


    @Scheduled(cron = "\${scheduler.exchange-rate.cron}")
    fun scheduleTaskUsingCronExpression() =
         exchangeRateService.synchronizeByDate(LocalDate.now())

}