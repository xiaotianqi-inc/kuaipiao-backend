package com.xiaotianqi.kuaipiao.controller

import com.xiaotianqi.kuaipiao.domain.dto.compliance.ComplianceAnalysisRequest
import com.xiaotianqi.kuaipiao.domain.dto.invoice.InvoiceResult
import com.xiaotianqi.kuaipiao.domain.dto.product.ProductClassificationData
import com.xiaotianqi.kuaipiao.domain.dto.product.ProductDto
import com.xiaotianqi.kuaipiao.domain.dto.product.ProductRequest
import kotlinx.coroutines.runBlocking
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import com.xiaotianqi.kuaipiao.service.*

@RestController
@RequestMapping("/ai")
@PreAuthorize("isAuthenticated()")
class AiController(private val aiService: AiService) {

    @PostMapping("/invoice/process")
    fun processInvoice(
        @RequestParam file: MultipartFile,
        @RequestParam country: String = "EC"
    ): ResponseEntity<InvoiceResult> = runBlocking {
        val result = aiService.processInvoice(file.bytes, country)
        ResponseEntity.ok(result)
    }

    @PostMapping("/product/classify")
    fun classifyProduct(@RequestBody request: ProductRequest): ResponseEntity<ProductClassificationData> = runBlocking {
        val result = aiService.classifyProduct(request.description, request.category)
        ResponseEntity.ok(result)
    }

    @PostMapping("/compliance/analyze")
    fun analyzeCompliance(@RequestBody transactionData: String): ResponseEntity<ComplianceAnalysisRequest> = runBlocking {
        val result = aiService.analyzeCompliance(transactionData)
        ResponseEntity.ok(result)
    }
}
