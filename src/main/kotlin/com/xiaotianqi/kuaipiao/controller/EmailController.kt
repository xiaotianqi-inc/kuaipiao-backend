package com.xiaotianqi.kuaipiao.controller

import kotlinx.coroutines.runBlocking
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import com.xiaotianqi.kuaipiao.domain.dto.email.ResendEmailRequest
import com.xiaotianqi.kuaipiao.client.ResendClient

@RestController
@RequestMapping("/email")
@PreAuthorize("hasAnyAuthority('MANAGE', 'CREATE', 'UPDATE', 'DELETE', 'VIEW')")
class EmailController(private val resendClient: ResendClient) {

    @PostMapping("/send")
    fun sendEmail(@RequestBody request: ResendEmailRequest): ResponseEntity<Map<String, String?>> = runBlocking {
        val emailId = resendClient.sendEmail(request)
        if (emailId != null) {
            ResponseEntity.status(HttpStatus.CREATED).body(mapOf("id" to emailId))
        } else {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(mapOf("error" to "Failed to send email"))
        }
    }

    @PostMapping("/batch")
    fun sendBatch(@RequestBody requests: List<ResendEmailRequest>): ResponseEntity<Map<String, Any>> = runBlocking {
        val ids = resendClient.sendBatchEmails(requests)
        if (ids != null) {
            ResponseEntity.status(HttpStatus.CREATED).body(
                mapOf("ids" to ids, "count" to ids.size)
            )
        } else {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(mapOf("error" to "Failed to send batch emails"))
        }
    }

    @PostMapping("/domains")
    fun createDomain(@RequestBody request: Map<String, String>): ResponseEntity<Any> = runBlocking {
        val domainName = request["name"] ?: return@runBlocking ResponseEntity.badRequest().build()
        val region = request["region"] ?: "us-east-1"

        val domain = resendClient.createDomain(domainName, region)
        if (domain != null) {
            ResponseEntity.status(HttpStatus.CREATED).body(domain)
        } else {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(mapOf("error" to "Failed to create domain"))
        }
    }

    @PostMapping("/domains/{domainId}/verify")
    fun verifyDomain(@PathVariable domainId: String): ResponseEntity<Map<String, Boolean>> = runBlocking {
        val verified = resendClient.verifyDomain(domainId)
        ResponseEntity.ok(mapOf("verified" to verified))
    }
}