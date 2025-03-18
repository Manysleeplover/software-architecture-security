package ru.romanov.apoibas.lab3.service

import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import ru.romanov.apoibas.lab3.model.dto.DailyExchangeRateDTO
import ru.romanov.apoibas.lab3.model.dto.ReportRequestDTO
import ru.romanov.apoibas.lab3.model.dto.ReportResponseDTO
import ru.romanov.apoibas.lab3.model.entity.DailyExchangeRateEntity
import ru.romanov.apoibas.lab3.repository.DailyExchangeRateRepository
import ru.romanov.apoibas.lab3.util.CnbResponseMapper
import ru.romanov.apoibas.lab3.util.formatDate
import ru.romanov.apoibas.lab3.util.toEntity
import java.time.LocalDate
import java.util.stream.Collectors

@Service
class ExchangeRateService(
    private val cnbRestClient: RestClient,
    private val cnbResponseMapper: CnbResponseMapper,
    private val dailyExchangeRateRepository: DailyExchangeRateRepository
) {

    fun synchronizeExchangeRateByDateInterval(startDate: LocalDate, endDate: LocalDate) {
        val responses = mutableListOf<DailyExchangeRateDTO>()

        for(targetDate in startDate.datesUntil(endDate)) {
            responses.add(
                synchronizeByDate(targetDate)
            )
        }
        dailyExchangeRateRepository.saveAll(responses.toEntity())
    }

    fun synchronizeByDate(targetDate: LocalDate): DailyExchangeRateDTO {
        val dto = cnbRestClient.get()
            .uri("daily.txt?date=${formatDate(targetDate)}")
            .retrieve()
            .body(String::class.java)?.let {
                cnbResponseMapper.map(targetDate, it)
            }
        dailyExchangeRateRepository.save(dto!!.toEntity())
        return dto
    }

    fun buildReport(request: ReportRequestDTO): ReportResponseDTO {
        val ratesList: List<DailyExchangeRateEntity> =
            dailyExchangeRateRepository.findDailyExchangeRateEntityByDateBetween(request.startDate, request.endDate)

        val doubleSummaryStatistics = ratesList
            .stream()
            .flatMap {
                it
                    .rates
                    .stream()
                    .filter {
                        exchangeRate -> exchangeRate
                            .code
                            .equals(
                                request.code
                            )
                    }
            }
            .collect(Collectors.summarizingDouble { it.rate })

        return ReportResponseDTO(
            min = doubleSummaryStatistics.min,
            max = doubleSummaryStatistics.max,
            avg = doubleSummaryStatistics.average
        )
    }
}