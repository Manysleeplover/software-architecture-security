package ru.romanov.apoibas.lab3.service

import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import ru.romanov.apoibas.lab3.model.dto.DailyExchangeRateDTO
import ru.romanov.apoibas.lab3.repository.DailyExchangeRateRepository
import ru.romanov.apoibas.lab3.util.CnbResponseMapper
import ru.romanov.apoibas.lab3.util.formatDate
import ru.romanov.apoibas.lab3.util.toEntity
import java.time.LocalDate

@Service
class CnbService(
    private val cnbRestClient: RestClient,
    private val cnbResponseMapper: CnbResponseMapper,
    private val dailyExchangeRateRepository: DailyExchangeRateRepository
) {

    fun synchronizeExchangeRateByDateInterval(startDate: LocalDate, endDate: LocalDate) {
        val responses = mutableListOf<DailyExchangeRateDTO>()

        for(targetDate in startDate.datesUntil(endDate)) {
            cnbRestClient
                .get()
                .uri("daily.txt?date=${formatDate(targetDate)}")
                .retrieve()
                .body(String::class.java)?.let {
                    responses.add(
                        cnbResponseMapper.map(targetDate, it)
                    )
                }
        }
        dailyExchangeRateRepository.saveAll(responses.toEntity())
    }

    fun synchronizeByDate(targetDate: LocalDate) {
        val entity = cnbRestClient.get()
            .uri("daily.txt?date=${formatDate(targetDate)}")
            .retrieve()
            .body(String::class.java)?.let {
                cnbResponseMapper.map(targetDate, it)
            }!!.toEntity()

        dailyExchangeRateRepository.save(entity)
    }
}