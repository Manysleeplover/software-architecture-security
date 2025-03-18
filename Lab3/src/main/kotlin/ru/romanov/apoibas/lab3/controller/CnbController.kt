package ru.romanov.apoibas.lab3.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.romanov.apoibas.lab3.model.dto.DailyExchangeRateDTO
import ru.romanov.apoibas.lab3.model.dto.DateIntervalDTO
import ru.romanov.apoibas.lab3.service.CnbService


@RestController
class CnbController(
    private val cnbController: CnbService,
) {

    @PostMapping("/sync/by-date-interval")
    fun synchronizeExchangeRateByDateInterval(
        @RequestBody request: DateIntervalDTO
    ){
        cnbController.synchronizeExchangeRateByDateInterval(request.startDate, request.endDate)
    }
}