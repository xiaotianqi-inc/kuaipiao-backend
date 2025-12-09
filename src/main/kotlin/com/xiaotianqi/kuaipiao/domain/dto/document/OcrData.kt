package com.xiaotianqi.kuaipiao.domain.dto.document

data class OcrResult(
    val extractedText: String,
    val confidence: Double,
    val pages: List<OcrPage>
)

data class OcrPage(
    val pageNumber: Int,
    val text: String,
    val confidence: Double
)
