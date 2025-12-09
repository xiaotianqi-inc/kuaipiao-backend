package com.xiaotianqi.kuaipiao.service

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class CacheService(
    private val redisTemplate: RedisTemplate<String, Any>
) {

    @Cacheable(value = ["invoices"], key = "#invoiceId")
    fun getInvoiceFromCache(invoiceId: String): Any? {
        return redisTemplate.opsForValue().get("invoice:$invoiceId")
    }

    fun setInvoiceCache(invoiceId: String, data: Any, ttl: Duration = Duration.ofHours(24)) {
        redisTemplate.opsForValue().set("invoice:$invoiceId", data, ttl)
    }

    @CacheEvict(value = ["invoices"], key = "#invoiceId")
    fun evictInvoiceCache(invoiceId: String) {
        redisTemplate.delete("invoice:$invoiceId")
    }

    fun getTariffClassification(productHash: String, origin: String, destination: String): Any? {
        return redisTemplate.opsForValue().get("tariff:$productHash:$origin:$destination")
    }

    fun setTariffClassification(
        productHash: String,
        origin: String,
        destination: String,
        data: Any,
        ttl: Duration = Duration.ofDays(7)
    ) {
        redisTemplate.opsForValue().set("tariff:$productHash:$origin:$destination", data, ttl)
    }

    fun getAiResponse(cacheKey: String): String? {
        return redisTemplate.opsForValue().get("ai:$cacheKey") as? String
    }

    fun setAiResponse(cacheKey: String, response: String, ttl: Duration = Duration.ofHours(1)) {
        redisTemplate.opsForValue().set("ai:$cacheKey", response, ttl)
    }
}