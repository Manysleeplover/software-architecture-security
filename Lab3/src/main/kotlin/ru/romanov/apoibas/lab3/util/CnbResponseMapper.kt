package ru.romanov.apoibas.lab3.util

import com.opencsv.CSVReaderBuilder
import com.opencsv.bean.CsvToBeanBuilder
import org.springframework.stereotype.Component
import ru.romanov.apoibas.lab3.model.dto.DailyExchangeRateDTO
import ru.romanov.apoibas.lab3.model.dto.ExchangeRateDTO
import java.io.StringReader
import java.time.LocalDate


@Component
class CnbResponseMapper(

) {

    fun map(targetDate: LocalDate, response: String): DailyExchangeRateDTO {
        return DailyExchangeRateDTO(
            targetDate,
            parseCSVToDailyExchangeRate(response),
        )
    }

    fun parseCSVToDailyExchangeRate(csvData: String): List<ExchangeRateDTO> =
        csvData.lines()
            .stream()
            .skip(2)
            .map { line -> line.split("|")}
            .filter{ it.size > 1 }
            .map { parts ->
                ExchangeRateDTO(
                    country = parts[0],
                    currency = parts[1],
                    amount = parts[2].toInt(),
                    code = parts[3],
                    rate = parts[4].toDouble()
                )
            }
            .toList()
}