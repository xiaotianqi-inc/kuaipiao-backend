package com.xiaotianqi.kuaipiao.domain.dto.business

import com.xiaotianqi.kuaipiao.domain.dto.validation.ValidationError
import com.xiaotianqi.kuaipiao.domain.dto.validation.ValidationWarning

data class BusinessRulesValidation(
    val isValid: Boolean,
    val errors: List<ValidationError>,
    val warnings: List<ValidationWarning>,
    val suggestions: List<String>
)
