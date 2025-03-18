package ru.romanov.apoibas.lab3.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.romanov.apoibas.lab3.model.dto.DateIntervalDTO
import ru.romanov.apoibas.lab3.model.dto.ReportRequestDTO
import ru.romanov.apoibas.lab3.model.dto.ReportResponseDTO
import ru.romanov.apoibas.lab3.service.ExchangeRateService


@RestController
class CnbController(
    private val exchangeRateService: ExchangeRateService,
) {

    @PostMapping("/sync/by-date-interval")
    fun synchronizeExchangeRateByDateInterval(
        @RequestBody request: DateIntervalDTO
    ){
        exchangeRateService.synchronizeExchangeRateByDateInterval(request.startDate, request.endDate)
    }

    @PostMapping("report/by-date-interval")
    fun reportExchangeRateByDateInterval(
        @RequestBody request: ReportRequestDTO
    ): ReportResponseDTO {
        return exchangeRateService.buildReport(request)
    }
}