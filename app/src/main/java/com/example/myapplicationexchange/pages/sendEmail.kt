package com.example.myapplicationexchange.pages

import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.CoroutineScope
fun sendEmail(subject: String, body: String, to: String) {
    val fromEmail = "dfrancohdez@gmail.com"  // Tu dirección de correo
    val password = "doii moah aqcv rnzb"   // Contraseña de la aplicación de Gmail

    val properties = Properties().apply {
        put("mail.smtp.auth", "true")
        put("mail.smtp.host", "smtp.gmail.com")
        put("mail.smtp.port", "465")
        put("mail.smtp.ssl.enable", "true")
    }

    val session = Session.getInstance(properties, object : Authenticator() {
        override fun getPasswordAuthentication(): PasswordAuthentication {
            return PasswordAuthentication(fromEmail, password)
        }
    })

    // Usar corutinas para enviar el correo en un hilo de fondo
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val message = MimeMessage(session).apply {
                setFrom(InternetAddress(fromEmail))
                addRecipient(Message.RecipientType.TO, InternetAddress(to))
                setSubject(subject)
                setText(body)
            }

            // Enviar el correo
            Transport.send(message)
            withContext(Dispatchers.Main) {
                println("Correo enviado exitosamente.")
            }
        } catch (e: MessagingException) {
            withContext(Dispatchers.Main) {
                println("Error al enviar correo: ${e.printStackTrace()}")
            }
        }
    }
}
