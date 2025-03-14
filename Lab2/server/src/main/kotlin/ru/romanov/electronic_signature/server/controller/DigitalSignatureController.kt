package ru.romanov.electronic_signature.server.controller

import org.springframework.web.bind.annotation.*
import ru.romanov.electronic_signature.server.ServerMessageResponse
import ru.romanov.electronic_signature.server.SignRequest
import ru.romanov.electronic_signature.server.VerifyRequest
import ru.romanov.electronic_signature.server.service.DigitalSignService


import java.util.*

@RestController
@RequestMapping("/api")
class DigitalSignatureController(
    private val digitalSignService: DigitalSignService
) {

    @GetMapping("/public-key")
    fun getPublicKey(): Map<String, String> {
        return mapOf("publicKey" to digitalSignService.getPublicKey())
    }

    @GetMapping("/random-message")
    fun getRandomMessage(): ServerMessageResponse {
        return digitalSignService.getRandomMessage()
    }

    // Подписать данные
    @PostMapping("/sign")
    fun signData(@RequestBody request: SignRequest): Map<String, Any> {
        val signature = digitalSignService.signData(request.message)
        return mapOf(
            "message" to request.message,
            "signature" to Base64.getEncoder().encodeToString(signature)
        )
    }

    // Проверить подпись
    @PostMapping("/verify")
    fun verifyData(@RequestBody request: VerifyRequest): Map<String, Boolean> {
        val signatureBytes = Base64.getDecoder().decode(request.signature)
        val isVerified = digitalSignService.verifySignature(request.message, signatureBytes)
        return mapOf("isVerified" to isVerified)
    }
}