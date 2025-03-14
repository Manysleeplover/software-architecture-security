package ru.romanov.electronic_signature.client.model

data class SignRequest(
    val message: String
)

data class VerifyRequest(
    val message: String,
    val signature: String?
)

data class VerifyResponse(
    val message: String,
    val signature: String
)

data class ServerMessageResponse(
    val message: String,
    val cipherMessage: String
)

data class ServerMessageRequest(
    val publicKey: String,
    val message: String,
    val cipherMessage: String
)