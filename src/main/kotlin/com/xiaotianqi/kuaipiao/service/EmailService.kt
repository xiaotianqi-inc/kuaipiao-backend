package com.xiaotianqi.kuaipiao.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import java.net.URLEncoder

@Service
class EmailService(
    private val mailSender: JavaMailSender,
    @Value("\${resend.from-email}") private val fromEmail: String,
    @Value("\${resend.from-name}") private val fromName: String,
    @Value("\${spring.application.base-url:http://localhost:8080}") private val baseUrl: String
) {

    fun sendVerificationEmail(email: String, token: String) {
        val verificationUrl = "$baseUrl/oauth/verify-email?token=${URLEncoder.encode(token, "UTF-8")}"

        val htmlContent = """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <style>
                    body { font-family: Arial, sans-serif; background-color: #f5f5f5; padding: 20px; }
                    .container { background-color: white; max-width: 600px; margin: 0 auto; padding: 40px; border-radius: 8px; }
                    .button { display: inline-block; background-color: #007bff; color: white; padding: 12px 30px; 
                              text-decoration: none; border-radius: 4px; margin: 20px 0; }
                </style>
            </head>
            <body>
                <div class="container">
                    <h1>Verify your email address</h1>
                    <p>Click the button below to verify your email:</p>
                    <a href="$verificationUrl" class="button">Verify Email</a>
                    <p>Or copy this link: $verificationUrl</p>
                </div>
            </body>
            </html>
        """.trimIndent()

        sendHtmlEmail(email, "Verify your email address", htmlContent)
    }

    fun sendPasswordResetEmail(email: String, token: String) {
        val resetUrl = "$baseUrl/oauth/reset-password?token=${URLEncoder.encode(token, "UTF-8")}"

        val htmlContent = """
            <!DOCTYPE html>
            <html>
            <body>
                <div style="max-width: 600px; margin: 0 auto; padding: 40px; background-color: white;">
                    <h1>Reset your password</h1>
                    <p>Click the link below to reset your password:</p>
                    <a href="$resetUrl" style="display: inline-block; background-color: #007bff; 
                       color: white; padding: 12px 30px; text-decoration: none; border-radius: 4px;">
                        Reset Password
                    </a>
                </div>
            </body>
            </html>
        """.trimIndent()

        sendHtmlEmail(email, "Reset your password", htmlContent)
    }

    private fun sendHtmlEmail(to: String, subject: String, htmlContent: String) {
        val message = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true, "UTF-8")

        helper.setFrom("$fromName <$fromEmail>")
        helper.setTo(to)
        helper.setSubject(subject)
        helper.setText(htmlContent, true)

        mailSender.send(message)
    }
}