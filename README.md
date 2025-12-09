# KuaiPiao API - Spring Boot

API empresarial con IA para procesamiento de facturas, clasificación de productos y análisis de compliance.

## 🚀 Stack Tecnológico

- **Framework**: Spring Boot 3.2
- **Lenguaje**: Kotlin 1.9
- **Base de datos**: PostgreSQL + Flyway
- **Cache**: Redis
- **Seguridad**: JWT + Spring Security
- **Documentación**: OpenAPI 3 (Swagger)
- **AI Providers**: OpenAI, DeepSeek, Anthropic, Google Vision
- **Email**: Resend API

## 📋 Requisitos

- JDK 17+
- PostgreSQL 15+
- Redis 7+
- Gradle 8+

## 🛠️ Configuración

### 1. Variables de Entorno

Crea un archivo `.env`:

```bash
# Database
POSTGRES_URL=jdbc:postgresql://localhost:5432/kuaipiao
POSTGRES_USER=postgres
POSTGRES_PASSWORD=nimda

# Redis
REDIS_HOST=localhost
REDIS_PORT=6379

# JWT
JWT_SECRET=your-super-secret-key-here-min-256-bits

# Email (Resend)
RESEND_API_KEY=re_xxxxxxxxxxxxx

# AI Providers
OPENAI_API_KEY=sk-xxxxxxxxxxxxx
DEEPSEEK_API_KEY=xxxxxxxxxxxxx
ANTHROPIC_API_KEY=sk-ant-xxxxxxxxxxxxx
GOOGLE_API_KEY=xxxxxxxxxxxxx
```

### 2. Base de Datos

```bash
# Con Docker Compose
docker-compose up -d postgres redis

# O manualmente
createdb kuaipiao
```

### 3. Build & Run

```bash
# Compilar
./gradlew build

# Ejecutar
./gradlew bootRun

# Tests
./gradlew test
```

## 📚 API Endpoints

### Autenticación
- `POST /oauth/sign-up` - Registro
- `POST /oauth/sign-in` - Login
- `GET /oauth/verify-email` - Verificar email
- `POST /oauth/logout` - Cerrar sesión

### Admin
- `POST /oauth/admin/sign-up` - Crear super admin
- `GET /oauth/admin/users` - Listar usuarios
- `DELETE /oauth/admin/users/{id}` - Eliminar usuario

### Empresas
- `POST /company/create` - Crear empresa
- `GET /company/find/id/{id}` - Buscar por ID
- `GET /company/find/tax-id/{taxId}` - Buscar por RUC/Tax ID

### Organizaciones
- `POST /org/create` - Crear organización
- `GET /org/find/id/{id}` - Buscar por ID
- `PUT /org/update/{id}/status` - Actualizar estado

### Enterprise
- `POST /enterprise/create` - Crear enterprise
- `PATCH /enterprise/update/{id}/plan` - Cambiar plan

### Email
- `POST /email/send` - Enviar email
- `POST /email/batch` - Envío masivo
- `POST /email/domains` - Crear dominio

### AI
- `POST /ai/invoice/process` - Procesar factura
- `POST /ai/product/classify` - Clasificar producto
- `POST /ai/compliance/analyze` - Análisis de compliance

### RBAC
- `POST /rbac/roles` - Crear rol
- `POST /rbac/users/{userId}/roles/{roleId}` - Asignar rol
- `GET /rbac/users/{userId}/permissions` - Permisos de usuario

## 🔐 Autenticación

La API usa JWT Bearer tokens:

```bash
# 1. Login
curl -X POST http://localhost:8080/api/v1/oauth/sign-in \
  -H "Content-Type: application/json" \
  -d '{"email":"user@example.com","password":"password123"}'

# Response: {"token": "eyJhbGc...", "user": {...}}

# 2. Usar token en requests
curl http://localhost:8080/api/v1/company/find/id/123 \
  -H "Authorization: Bearer eyJhbGc..."
```

## 📖 Documentación

Swagger UI disponible en:
```
http://localhost:8080/api/v1/swagger-ui.html
```

OpenAPI JSON:
```
http://localhost:8080/api/v1/api-docs
```

## 🧪 Testing

```bash
# Todos los tests
./gradlew test

# Tests específicos
./gradlew test --tests AuthControllerTest

# Con coverage
./gradlew test jacocoTestReport
```

## 🐳 Docker

```bash
# Build imagen
docker build -t kuaipiao-api .

# Run
docker run -p 8080:8080 \
  -e POSTGRES_URL=jdbc:postgresql://host.docker.internal:5432/kuaipiao \
  -e JWT_SECRET=your-secret \
  kuaipiao-api
```

## 📁 Estructura del Proyecto

```
src/main/kotlin/org/xiaotianqi/kuaipiao/
├── config/              # Configuraciones (Security, Redis, AI)
├── controller/          # REST Controllers
├── service/             # Lógica de negocio
├── repository/          # JPA Repositories
├── domain/
│   ├── entity/          # Entidades JPA
│   └── auth/            # DTOs
├── client/              # Clientes externos (AI, Email)
├── security/            # JWT, Filters
└── exception/           # Exception Handlers

src/main/resources/
├── application.yml      # Configuración
└── db/migration/        # Flyway migrations
```

## 🔄 Diferencias vs Ktor

| Ktor | Spring Boot |
|------|-------------|
| `routing {}` | `@RestController` |
| `call.receive<T>()` | `@RequestBody` |
| `call.respond()` | `ResponseEntity` |
| Koin DI | `@Autowired` |
| Exposed | JPA/Hibernate |
| Sessions | JWT Stateless |

## 📝 Notas

- Flyway ejecuta migraciones automáticamente
- Redis cache configurado con TTL de 1 hora
- JWT expira en 24 horas
- Logs en `logs/kuaipiao.log`
- Permisos: VIEW, CREATE, UPDATE, DELETE, MANAGE

## 🐛 Troubleshooting

**Error: "Could not connect to Redis"**
```bash
docker-compose up -d redis
```

**Error: Flyway migration failed**
```bash
# Limpiar y reiniciar
docker-compose down -v
docker-compose up -d postgres
./gradlew flywayClean flywayMigrate
```

**Error: JWT secret demasiado corto**
```bash
# Genera uno seguro:
openssl rand -base64 32
```
