package com.xiaotianqi.kuaipiao.scripts

import com.xiaotianqi.kuaipiao.domain.entity.ai.AiCache
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.tool.schema.TargetType
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile
import org.hibernate.tool.schema.spi.SchemaManagementTool
import org.hibernate.tool.schema.spi.ExecutionOptions
import org.hibernate.tool.schema.spi.TargetDescriptor
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import com.xiaotianqi.kuaipiao.domain.entity.enterprise.*
import com.xiaotianqi.kuaipiao.domain.entity.organization.*
import com.xiaotianqi.kuaipiao.domain.entity.rbac.*
import com.xiaotianqi.kuaipiao.domain.entity.user.*
import com.xiaotianqi.kuaipiao.domain.entity.company.*
import com.xiaotianqi.kuaipiao.domain.entity.ai.*
import com.xiaotianqi.kuaipiao.domain.entity.benchmark.*
import com.xiaotianqi.kuaipiao.domain.entity.invoice.*
import org.springframework.boot.persistence.autoconfigure.EntityScan
import java.io.File
import java.nio.file.Files
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.EnumSet
import kotlin.reflect.KClass

@SpringBootApplication
@EntityScan("com.xiaotianqi.kuaipiao.domain.entity")
@Profile("migration-generator")
class MigrationGeneratorApplication {

    @Bean
    fun generateMigrations(): CommandLineRunner {
        return CommandLineRunner {
            println("🚀 Generando migraciones modulares desde entidades JPA...")

            val generator = HibernateDDLGenerator()

            val modules = mapOf(
                "enterprise_tables" to listOf(
                    Enterprise::class,
                    EnterpriseAuditLog::class,
                    EnterpriseBackup::class,
                    EnterpriseMigration::class
                ),
                "organization_tables" to listOf(
                    Organization::class
                ),
                "user_tables" to listOf(
                    User::class,
                    EmailVerification::class,
                    PasswordReset::class
                ),
                "rbac_tables" to listOf(
                    Role::class,
                    Permission::class,
                    UserRole::class
                ),
                "company_tables" to listOf(
                    Company::class,
                    FiscalYear::class
                ),
                "ai_table" to listOf(
                    AiCache::class,
                    AiModelResult::class,
                    ComplianceRisk::class,
                    DocumentProcessing::class,
                    RiskPattern::class
                ),
                "benchmark_table" to listOf(
                    Benchmark::class
                ),
                "invoice_table" to listOf(
                    Invoice::class
                )
            )

            val migrationsPath = createMigrationsFolder()
            val currentVersion = getLastMigrationVersion(migrationsPath)
            var nextVersion = currentVersion + 1

            modules.forEach { (moduleName, entityClasses) ->
                val sql = generator.generateDDL(entityClasses)
                val formattedSql = formatSql(sql)

                val fileName = "V${nextVersion}__${moduleName}.sql"
                val file = File(migrationsPath, fileName)
                file.writeText(formattedSql)

                println("✅ Migración creada: ${file.name} (${entityClasses.size} entidades)")
                nextVersion++
            }

            println("🎉 Todas las migraciones fueron generadas exitosamente en: ${migrationsPath.absolutePath}")

            System.exit(0)
        }
    }
}

class HibernateDDLGenerator {

    fun generateDDL(entityClasses: List<KClass<*>>): String {
        val settings = mapOf(
            "hibernate.dialect" to "org.hibernate.dialect.PostgreSQLDialect",
            "hibernate.hbm2ddl.auto" to "create",
            "hibernate.format_sql" to "true",
            "hibernate.use_sql_comments" to "false"
        )

        val serviceRegistry = StandardServiceRegistryBuilder()
            .applySettings(settings)
            .build()

        val metadata = MetadataSources(serviceRegistry).apply {
            entityClasses.forEach { addAnnotatedClass(it.java) }
        }.buildMetadata()

        val outputFile = Files.createTempFile("schema", ".sql").toFile()

        try {
            val scriptTarget = ScriptTargetOutputToFile(outputFile, "UTF-8")

            val schemaCreator = serviceRegistry
                .getService(SchemaManagementTool::class.java)
                ?.getSchemaCreator(mapOf<String, Any>())

            val method = schemaCreator?.javaClass?.methods?.find {
                it.name == "doCreation" && it.parameterCount == 4
            }

            val executionOptions = object : ExecutionOptions {
                override fun shouldManageNamespaces() = true
                override fun getConfigurationValues() = emptyMap<String, Any>()
                override fun getExceptionHandler() = null
            }

            val targetDescriptor = object : TargetDescriptor {
                override fun getTargetTypes() = EnumSet.of(TargetType.SCRIPT)
                override fun getScriptTargetOutput() = scriptTarget
            }

            // Intentar con lambda
            try {
                method?.invoke(
                    schemaCreator,
                    metadata,
                    executionOptions,
                    { _: Any -> true },
                    targetDescriptor
                )
            } catch (_: Exception) {
                val sourceDescriptor = object : org.hibernate.tool.schema.spi.SourceDescriptor {
                    override fun getSourceType() = org.hibernate.tool.schema.SourceType.METADATA
                    override fun getScriptSourceInput() = null
                }
                method?.invoke(
                    schemaCreator,
                    metadata,
                    executionOptions,
                    sourceDescriptor,
                    targetDescriptor
                )
            }

            return outputFile.readText()
        } finally {
            outputFile.delete()
            serviceRegistry.close()
        }
    }
}

fun createMigrationsFolder(): File {
    val basePath = "src/main/resources/db/migration"
    val folder = File(basePath)

    if (!folder.exists()) {
        folder.mkdirs()
        println("📁 Carpeta de migraciones creada: ${folder.absolutePath}")
    }

    return folder
}

fun getLastMigrationVersion(folder: File): Int {
    val regex = Regex("""V(\d+)__.*\.sql""")
    val versions = folder.listFiles()
        ?.mapNotNull { file ->
            regex.find(file.name)?.groupValues?.get(1)?.toIntOrNull()
        }
        ?: emptyList()

    return versions.maxOrNull() ?: 0
}

fun formatSql(sql: String): String {
    val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

    val header = """
        -- Generated: $timestamp
        -- KuaiPiao Database Migration
        -- DO NOT EDIT THIS FILE MANUALLY
        
    """.trimIndent()

    val cleaned = sql
        .lines()
        .filterNot { line ->
            line.trim().isEmpty() ||
                    line.trim().startsWith("drop table") ||
                    line.trim().startsWith("drop sequence")
        }
        .joinToString("\n")
        .replace(Regex("create table"), "\nCREATE TABLE IF NOT EXISTS")
        .replace(Regex("alter table"), "\nALTER TABLE")
        .replace(Regex("create index"), "\nCREATE INDEX IF NOT EXISTS")
        .replace(Regex("create sequence"), "\nCREATE SEQUENCE IF NOT EXISTS")
        .trim()

    return "$header\n$cleaned\n"
}

fun main(args: Array<String>) {
    System.setProperty("spring.profiles.active", "migration-generator")
    SpringApplication.run(MigrationGeneratorApplication::class.java, *args)
}