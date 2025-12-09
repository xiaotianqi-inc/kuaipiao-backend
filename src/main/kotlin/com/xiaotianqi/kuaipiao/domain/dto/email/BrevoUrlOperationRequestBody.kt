package com.xiaotianqi.kuaipiao.domain.dto.email

data class BrevoUrlOperationRequestBody(
    val to: List<BrevoEmailField>,
    val templateId: Long,
    val params: Params
) {
    data class Params(
        val url: String
    )
}