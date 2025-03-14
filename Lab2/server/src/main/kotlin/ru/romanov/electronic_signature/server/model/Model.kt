package ru.romanov.electronic_signature.server.model

data class SignRequest(
    val message: String
)

data class VerifyRequest(
    val message: String,
    val signature: String
)

data class ServerMessageResponse(
    val message: String,
    val cipherMessage: String
)