package com.xiaotianqi.kuaipiao.domain.dto.embedding

data class EmbeddingRequest(
    val model: String,
    val input: String
)

data class EmbeddingResponse(
    val data: List<EmbeddingData>
)

data class EmbeddingData(
    val embedding: List<Double>,
    val index: Int
)
