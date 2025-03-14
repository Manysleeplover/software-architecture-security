package ru.romanov.electronic_signature.client.controller

import org.bouncycastle.asn1.x509.ObjectDigestInfo.publicKey
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClient
import ru.romanov.electronic_signature.client.model.*
import ru.romanov.electronic_signature.client.service.DigitalSignService
import java.util.*


@RestController
class SignMessageController(
    val serverRestClient: RestClient,
    val digitalSignService: DigitalSignService
) {

    @GetMapping("/public-key")
    fun getPublicKey(): ResponseEntity<String> =
        serverRestClient
            .get()
            .uri("/api/public-key")
            .retrieve()
            .toEntity(String::class.java)


    @GetMapping("/client-public-key")
    fun getClientPublicKey() = ResponseEntity.ok(serverRestClient)


    @GetMapping("/random-message")
    fun getRandomMessage(): ResponseEntity<ServerMessageResponse> =
        serverRestClient
            .get()
            .uri("/api/random-message")
            .retrieve()
            .toEntity(ServerMessageResponse::class.java)

    // Подписать данные
    @PostMapping("/sign")
    fun signData(@RequestBody request: SignRequest): VerifyResponse =
     VerifyResponse(
         request.message,
         Base64
             .getEncoder()
             .encodeToString(
                 digitalSignService.signData(
                     request.message
                 )
             )
     )

    @PostMapping("/verify-on-client")
    fun verifyDataOnClient(@RequestBody request: ServerMessageRequest) =
        digitalSignService.verifySignature(cipherData = request.cipherMessage, data = request.message, publicKey = request.publicKey)


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