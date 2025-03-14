package ru.romanov.electronic_signature.client.service

import org.springframework.stereotype.Service
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.Signature
import java.security.spec.X509EncodedKeySpec
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

    //Верифицировать подпись
    fun verifySignature(data: String, signature: ByteArray): Boolean {
        val publicKey = publicKey
        val verifySignature = Signature.getInstance("SHA256withRSA")
        verifySignature.initVerify(publicKey)
        verifySignature.update(data.toByteArray())
        return verifySignature.verify(signature)
    }

    fun verifySignature(cipherData: String, publicKey: String, data: String): Boolean {
        val keySpec = X509EncodedKeySpec(Base64.getDecoder().decode(publicKey))
        // Получаем KeyFactory для алгоритма RSA
        val keyFactory: KeyFactory = KeyFactory.getInstance("RSA")
        // Генерируем PublicKey
        val publicKey = keyFactory.generatePublic(keySpec)
        val sig = Signature.getInstance("SHA256withRSA")
        sig.initVerify(publicKey)
        sig.update(data.toByteArray())
        val signatureBytes: ByteArray = Base64.getDecoder().decode(cipherData.toByteArray())
        return sig.verify(signatureBytes)
    }
}
