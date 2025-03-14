package ru.romanov.electronic_signature.client.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClient
import ru.romanov.electronic_signature.client.model.SignRequest
import ru.romanov.electronic_signature.client.model.VerifyRequest
import ru.romanov.electronic_signature.client.model.VerifyResponse
import ru.romanov.electronic_signature.client.service.DigitalSignService
import java.util.*


@RestController
class SignMessageController(
    val serverRestClient: RestClient,
    val digitalSignService: DigitalSignService
) {

    // Подписать данные
    @PostMapping("/sign")
    fun signData(@RequestBody request: SignRequest): VerifyResponse {
        val signature = digitalSignService.signData(request.message)
        return VerifyResponse(request.message, Base64.getEncoder().encodeToString(signature))

    }

    // Проверить подпись
    @PostMapping("/verify")
    fun verifyData(@RequestBody request: VerifyRequest) =
        serverRestClient
            .post()
            .uri("/api/verify")
            .body(request)
            .retrieve()
            .toEntity(String::class.java)
}