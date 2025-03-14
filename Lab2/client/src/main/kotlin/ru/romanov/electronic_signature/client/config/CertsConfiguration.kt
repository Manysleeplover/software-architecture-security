package ru.romanov.electronic_signature.client.config

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo
import org.bouncycastle.openssl.PEMParser
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileReader
import java.security.PrivateKey
import java.security.PublicKey

@Configuration
class CertsConfiguration {

    @Bean
    fun privateKeyPem(): PrivateKey {
        val pemParser = PEMParser(FileReader("C:\\Users\\ilyar\\OneDrive\\Рабочий стол\\Проекты\\Архитектура, программное обеспечение и безопасность автоматизированных систем\\Lab2\\certs\\priv.pem"))
        val pemObject = pemParser.readObject()
        val converter = JcaPEMKeyConverter()

        return when (pemObject) {
            is PrivateKeyInfo -> converter.getPrivateKey(pemObject)
            else -> throw IllegalArgumentException("Invalid PEM file format")
        }

    }

    @Bean
    fun publicKeyPem(): PublicKey {
        val pemParser = PEMParser(FileReader("C:\\Users\\ilyar\\OneDrive\\Рабочий стол\\Проекты\\Архитектура, программное обеспечение и безопасность автоматизированных систем\\Lab2\\certs\\pub.pem"))
        val pemObject = pemParser.readObject()
        val converter = JcaPEMKeyConverter()
        return when (pemObject) {
            is SubjectPublicKeyInfo -> converter.getPublicKey(pemObject)
            else -> throw IllegalArgumentException("Invalid PEM file format")
        }
    }
}