package com.xiaotianqi.kuaipiao.service

import com.xiaotianqi.kuaipiao.config.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.Date
import javax.crypto.SecretKey

@Service
class JwtService(
    @Qualifier("jwtProperties") private val props: JwtProperties
) {
    private val key: SecretKey by lazy {
        require(props.secret.isNotBlank()) { "JWT secret is empty" }
        Keys.hmacShaKeyFor(props.secret.toByteArray())
    }

    fun generateToken(
        userId: String,
        email: String,
        roles: List<String> = emptyList()
    ): String {
        val now = Date()
        val expiryDate = Date(now.time + props.expiration)

        return Jwts.builder()
            .subject(email)
            .issuer(props.issuer)
            .audience().add(props.audience).and()
            .issuedAt(now)
            .expiration(expiryDate)
            .claim("userId", userId)
            .claim("roles", roles)
            .signWith(key, Jwts.SIG.HS256)
            .compact()
    }

    fun extractUsername(token: String): String =
        extractClaim(token, Claims::getSubject)

    fun extractUserId(token: String) =
        extractClaim(token) { it["userId"] as String }

    fun extractRoles(token: String): List<String> =
        extractClaim(token) {
            @Suppress("UNCHECKED_CAST")
            it["roles"] as? List<String> ?: emptyList()
        }

    fun <T> extractClaim(token: String, resolver: (Claims) -> T): T =
        resolver(extractAllClaims(token))

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean =
        extractUsername(token) == userDetails.username && !isTokenExpired(token)

    private fun isTokenExpired(token: String) =
        extractExpiration(token).before(Date())

    private fun extractExpiration(token: String) =
        extractClaim(token, Claims::getExpiration)

    private fun extractAllClaims(token: String): Claims =
        Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload
}