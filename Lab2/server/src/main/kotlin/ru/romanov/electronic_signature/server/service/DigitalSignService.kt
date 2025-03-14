package ru.romanov.electronic_signature.server.service

import org.springframework.stereotype.Service
import ru.romanov.electronic_signature.server.ServerMessageResponse
import java.security.PrivateKey
import java.security.PublicKey
import java.security.Signature
import java.util.*

@Service
class DigitalSignService(
    private val publicKey: PublicKey,
    private val privateKey: PrivateKey,
) {

    fun getPublicKey(): String {
        return Base64.getEncoder().encodeToString(publicKey.encoded)
    }

    // Подписать данные
    fun signData(data: String): ByteArray {
        val signature = Signature.getInstance("SHA256withRSA")
        signature.initSign(privateKey)
        signature.update(data.toByteArray())
        return signature.sign()
    }

    fun verifySignature(data: String, signature: ByteArray): Boolean {
        val publicKey = publicKey
        val verifySignature = Signature.getInstance("SHA256withRSA")
        verifySignature.initVerify(publicKey)
        verifySignature.update(data.toByteArray())
        return verifySignature.verify(signature)
    }


    fun getRandomMessage(): ServerMessageResponse {

        val message = UUID.randomUUID().toString()
        val cipherMessage = Base64.getEncoder().encodeToString(signData(message))

        return ServerMessageResponse(message, cipherMessage)
    }

}
