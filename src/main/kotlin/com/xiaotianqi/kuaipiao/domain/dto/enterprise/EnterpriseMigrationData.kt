package com.xiaotianqi.kuaipiao.domain.dto.enterprise

import com.xiaotianqi.kuaipiao.enums.OperationStatus

data class EnterpriseMigrationData(
    val id: String,
    val enterpriseId: String,
    val version: String,
    val description: String,
    val script: String,
    val status: OperationStatus = OperationStatus.PENDING,
    val appliedAt: Long,
    val executionTime: Long,
    val errorMessage: String? = null,
    val checksum: String? = null,
)

data class EnterpriseMigrationCreateData(
    val enterpriseId: String,
    val version: String,
    val description: String,
    val script: String,
)

data class EnterpriseMigrationResponse(
    val id: String,
    val enterpriseId: String,
    val version: String,
    val status: OperationStatus,
    val appliedAt: Long,
)
