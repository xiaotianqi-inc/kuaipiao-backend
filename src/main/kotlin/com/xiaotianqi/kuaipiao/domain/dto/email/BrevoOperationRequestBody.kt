package com.xiaotianqi.kuaipiao.domain.dto.email

data class BrevoOperationRequestBody(
    val to: List<BrevoEmailField>,
    val templateId: Long
)