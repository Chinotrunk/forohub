# 📚 ForoHub API - Challenge Alura/ONE

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.2-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![JWT](https://img.shields.io/badge/JWT-Security-red)
![License](https://img.shields.io/badge/License-MIT-yellow)

## 📋 Descripción

**ForoHub** es una API REST desarrollada como parte del **Challenge Backend de Alura Latam y Oracle Next Education (ONE)**. 

Se trata de un sistema de foro de discusión donde los usuarios pueden:
- 🔐 Autenticarse mediante JWT
- 📝 Crear, consultar, actualizar y eliminar **tópicos**
- 💬 Responder tópicos
- 📚 Gestionar cursos
- 👥 Administrar usuarios

La API implementa **autenticación basada en tokens JWT**, validaciones con **Bean Validation**, persistencia con **JPA/Hibernate** y migraciones de base de datos con **Flyway**.

---

## 🚀 Características principales

- ✅ **CRUD completo** de tópicos, respuestas, cursos y usuarios
- ✅ **Autenticación JWT** con Spring Security
- ✅ **Validaciones** con Bean Validation
- ✅ **Paginación** en listados
- ✅ **Migraciones automáticas** con Flyway
- ✅ **Relaciones entre entidades** (Usuario-Perfil, Tópico-Curso-Respuestas)
- ✅ **Regla de negocio**: No permitir tópicos duplicados (mismo título y mensaje)
- ✅ **Documentación interactiva** con Swagger/OpenAPI

---

## 🛠️ Tecnologías utilizadas

| Tecnología | Versión | Uso |
|------------|---------|-----|
| Java | 17 | Lenguaje de programación |
| Spring Boot | 4.0.2 | Framework principal |
| Spring Security | 7.0.2 | Autenticación y autorización |
| Spring Data JPA | 4.0.2 | Persistencia de datos |
| Hibernate | 7.2.1 | ORM |
| MySQL | 8.0 | Base de datos |
| Flyway | 11.14.1 | Migraciones de BD |
| Auth0 JWT | 4.4.0 | Generación y validación de tokens |
| Lombok | 1.18.42 | Reducción de código boilerplate |
| Bean Validation | 3.1.1 | Validaciones |
| Springdoc OpenAPI | 2.6.0 | Documentación Swagger |
| Maven | 3.x | Gestión de dependencias |

---

## 📦 Estructura del proyecto
---
```
forohub/
├── src/main/java/com/davidlima/forohub/
│   ├── controller/          # Controladores REST
│   │   ├── AutenticacionController.java
│   │   ├── CursoController.java
│   │   ├── RespuestaController.java
│   │   ├── TopicoController.java
│   │   └── UsuarioController.java
│   ├── domain/              # Entidades JPA
│   │   ├── curso/
│   │   ├── respuesta/
│   │   ├── topico/
│   │   └── usuario/
│   ├── repository/          # Repositorios JPA
│   ├── service/             # Lógica de negocio
│   ├── security/            # Configuración de seguridad
│   │   ├── JwtAuthenticationFilter.java
│   │   ├── TokenService.java
│   │   └── SecurityConfigurations.java
│   └── infra/               # Configuraciones y excepciones
├── src/main/resources/
│   ├── application.properties
│   └── db/migration/        # Scripts Flyway
└── pom.xml
```
---

## ⚙️ Configuración e instalación

1️⃣ Requisitos previos
Java 17 o superior
Maven 3.x
MySQL 8.0 o superior
Git

2️⃣ Clonar el repositorio
bash
git clone https://github.com/tu-usuario/forohub.git
cd forohub

3️⃣ Configurar la base de datos
Crea la base de datos en MySQL:
SQL
CREATE DATABASE forohub;

4️⃣ Configurar application.properties
Edita src/main/resources/application.properties:

properties
spring.application.name=forohub

# Configuración de MySQL
```
spring.datasource.url=jdbc:mysql://localhost:3306/forohub
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Flyway
spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true

# JWT
jwt.secret=tu-clave-secreta-super-segura-cambiar-en-producción
jwt.expiration=3600000
```
5️⃣ Ejecutar migraciones y arrancar la aplicación
bash
mvn clean install
mvn spring-boot:run

La aplicación estará disponible en: http://localhost:8080

# 🔐 Autenticación

Obtener token JWT
Endpoint: POST /login

Request:
```
{
  "correoElectronico": "david@gmail.com",
  "contrasena": "123456"
}
```
Response:
```
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```
Usar el token
En todas las peticiones protegidas, incluir el header:
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```
# 📚 Endpoints principales

🔓 Públicos (sin autenticación)

<img width="463" height="142" alt="image" src="https://github.com/user-attachments/assets/6122c47c-9046-4fc8-89e4-dd08317c2aa3" />

🔒 Protegidos (requieren JWT)

👥 Usuarios

<img width="489" height="224" alt="image" src="https://github.com/user-attachments/assets/093f79a3-244f-42ef-b78b-a245987b4213" />

📝 Tópicos

<img width="641" height="290" alt="image" src="https://github.com/user-attachments/assets/acb7c928-f43a-4d77-9053-4961033a2d85" />

📚 Cursos

<img width="451" height="250" alt="image" src="https://github.com/user-attachments/assets/fa129f8c-c14a-4640-9fcc-d0de40f2d176" />

💬 Respuestas

<img width="635" height="213" alt="image" src="https://github.com/user-attachments/assets/fbbda013-2931-4bff-bdea-92e93257f3da" />


# 📝 Ejemplos de uso
Crear un tópico
Request:
```POST /topicos
Authorization: Bearer TU_TOKEN
Content-Type: application/json

{
  "titulo": "¿Cómo usar Spring Security?",
  "mensaje": "Necesito ayuda con la configuración de JWT",
  "autorId": 1,
  "cursoId": 1
}
```
Response:
```{
  "id": 4,
  "titulo": "¿Cómo usar Spring Security?",
  "mensaje": "Necesito ayuda con la configuración de JWT",
  "fechaCreacion": "2026-02-18T00:00:00",
  "status": "ABIERTO",
  "autor": "David",
  "curso": "Spring Boot"
}
```
Listar tópicos (paginado)
Request:
```
GET /topicos?page=0&size=10
Authorization: Bearer TU_TOKEN
```
Response:
```
{
  "content": [
    {
      "id": 1,
      "titulo": "¿Cómo funciona @Transactional?",
      "mensaje": "Tengo dudas sobre el alcance...",
      "fechaCreacion": "2026-02-17T23:30:00",
      "status": "ABIERTO",
      "autor": "David",
      "curso": "Spring Boot"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10
  },
  "totalElements": 1,
  "totalPages": 1
}
```

# 🗄️ Modelo de datos
Diagrama de relaciones
```
┌─────────────┐       ┌──────────────┐       ┌─────────────┐
│   Usuario   │──────<│UsuariosPerfil│>──────│   Perfil    │
└─────────────┘       └──────────────┘       └─────────────┘
       │                                              
       │ 1:N                                          
       ▼                                              
┌─────────────┐       ┌──────────────┐                
│   Tópico    │───────│    Curso     │                
└─────────────┘ N:1   └──────────────┘                
       │                                              
       │ 1:N                                          
       ▼                                              
┌─────────────┐                                       
│  Respuesta  │                                       
└─────────────┘
```
# 🔒 Seguridad
- ✅ Contraseñas encriptadas con BCrypt
- ��� Tokens JWT firmados con HMAC256
- ✅ Expiración de tokens configurable (jwt.expiration)
- ✅ Filtro de autenticación personalizado
- ✅ Endpoints públicos y protegidos bien definidos
# ⚠️ Importante para producción
- Cambiar jwt.secret por un valor secreto y seguro
- Usar variables de entorno para credenciales
- Configurar HTTPS
- Implementar rate limiting
- Agregar logs de auditoría
---

# 📖 Documentación adicional
Migraciones Flyway
Las migraciones se encuentran en src/main/resources/db/migration/:
```
V1__create_tables.sql - Crea todas las tablas
V2__insert_default_user.sql - Usuario inicial
V3__insert_perfiles_and_relation.sql - Perfil y relación
V4__insert_default_cursos.sql - Cursos de ejemplo
V5__insert_default_topicos.sql - Tópicos de ejemplo
V6__insert_default_respuestas.sql - Respuestas de ejemplo
```
---
# 🤝 Contribuciones
Este es un proyecto de desafío educativo. Si encuentras mejoras, siéntete libre de:

1. Hacer fork del proyecto
2. Crear una rama (git checkout -b feature/mejora)
3. Commit de cambios (git commit -m 'Agrega nueva funcionalidad')
3. Push a la rama (git push origin feature/mejora)
4. Abrir un Pull Request
---

# 👨‍💻 Autor
- David Lima
- Challenge Backend - Alura Latam & Oracle Next Education (ONE)
- Fecha: Febrero 2026

