package com.xiaotianqi.kuaipiao.domain.dto.document

import java.time.Instant
import java.time.ZoneId
import java.time.temporal.ChronoUnit

data class DateRange(
    val start: Instant,
    val end: Instant
) {
    companion object {
        fun last30Days(): DateRange {
            val now = Instant.now()
            val timeZone = ZoneId.systemDefault()
            val start = now.minus(30, ChronoUnit.DAYS)
            val end = Instant.now()
            return DateRange(start, end)
        }

        fun last90Days(): DateRange {
            val now = Instant.now()
            val timeZone = ZoneId.systemDefault()
            val start = now.minus(90, ChronoUnit.DAYS)
            val end = Instant.now()
            return DateRange(start, end)
        }
    }
}
