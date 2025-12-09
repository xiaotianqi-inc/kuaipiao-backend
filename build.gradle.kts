plugins {
	kotlin("jvm") version "2.2.21"
	kotlin("plugin.spring") version "2.2.21"
	id("org.springframework.boot") version "4.0.0"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("plugin.jpa") version "2.2.21"
}

group = "com.xiaotianqi"
version = "0.0.1-SNAPSHOT"
description = "Kuaipiao is a modern, multiplatform system"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(24)
	}
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2025.1.0"

dependencies {
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-amqp")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-flyway")
    implementation("org.springframework.boot:spring-boot-starter-graphql")
    implementation("org.springframework.boot:spring-boot-starter-kafka")
    implementation("org.springframework.boot:spring-boot-starter-restclient")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-security-oauth2-authorization-server")
    implementation("org.springframework.boot:spring-boot-starter-webservices")

    // Validation
    implementation("io.konform:konform-jvm:0.11.1")
    implementation("io.konform:konform:0.11.1")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    // JWT
    implementation("io.jsonwebtoken:jjwt-api:0.12.3")
    implementation("io.jsonwebtoken:jjwt-impl:0.12.3")
    implementation("io.jsonwebtoken:jjwt-jackson:0.12.3")

    // Database
    implementation("org.postgresql:postgresql")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("org.hibernate.orm:hibernate-core:7.2.0.CR3")
    implementation("org.reflections:reflections:0.10.2")
    implementation("jakarta.persistence:jakarta.persistence-api:3.2.0")
    runtimeOnly("org.postgresql:postgresql")

    // Redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("io.lettuce:lettuce-core")

    // HTTP Client
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // OpenAPI/Swagger
    implementation("com.scalar.maven:scalar:0.4.4")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
    implementation("org.springdoc:springdoc-openapi-starter-common:2.5.0")
    implementation("io.swagger.core.v3:swagger-models:2.2.21")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.21")
    implementation("io.swagger.core.v3:swagger-core:2.2.21")

    // BCrypt
    implementation("org.mindrot:jbcrypt:0.4")

    // Apache POI (Excel)
    implementation("org.apache.poi:poi:5.2.5")
    implementation("org.apache.poi:poi-ooxml:5.4.0")

    // Monitoring and Logging
    implementation("io.github.oshai:kotlin-logging-jvm:5.1.0")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")

    // Resilience4j
    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")

    // Serialization XML (Kotlin)
    implementation("io.github.pdvrieze.xmlutil:serialization-jvm:0.91.3")
    implementation("org.apache.santuario:xmlsec:4.0.4")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.7.3")

    // Xades
    implementation("org.bouncycastle:bcpkix-jdk18on:1.79")
    implementation("org.bouncycastle:bcprov-jdk18on:1.78.1")

    // Jaxb for XML Binding
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.2")
    implementation("org.glassfish.jaxb:jaxb-runtime:4.0.5")

    // Owasp Encoder for Security
    implementation("org.owasp.encoder:encoder:1.4.0")

    // Env
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")

    // Test
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
    testImplementation("org.springframework.boot:spring-boot-starter-actuator-test")
    testImplementation("org.springframework.boot:spring-boot-starter-amqp-test")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
    testImplementation("org.springframework.boot:spring-boot-starter-data-redis-test")
    testImplementation("org.springframework.boot:spring-boot-starter-flyway-test")
    testImplementation("org.springframework.boot:spring-boot-starter-graphql-test")
    testImplementation("org.springframework.boot:spring-boot-starter-kafka-test")
    testImplementation("org.springframework.boot:spring-boot-starter-restclient-test")
    testImplementation("org.springframework.boot:spring-boot-starter-security-oauth2-authorization-server-test")
    testImplementation("org.springframework.boot:spring-boot-starter-security-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webservices-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.testcontainers:testcontainers-junit-jupiter")
    testImplementation("org.testcontainers:testcontainers-kafka")
    testImplementation("org.testcontainers:testcontainers-postgresql")
    testImplementation("org.testcontainers:testcontainers-rabbitmq")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks {
    bootRun {
        mainClass.set("com.xiaotianqi.kuaipiao.KuaipiaoApplicationKt")
    }
}

tasks.register<JavaExec>("generateJwt") {
    group = "cli"
    description = "Generate JWT without starting Spring Boot"

    mainClass.set("com.xiaotianqi.kuaipiao.cli.JwtGenerator")
    classpath = sourceSets["main"].runtimeClasspath
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.register("generateMigrations", JavaExec::class) {
    group = "database"
    description = "Genera migraciones SQL separadas por tabla como Laravel"

    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("com.xiaotianqi.kuaipiao.scripts.GenerateMigrationsKt")
}