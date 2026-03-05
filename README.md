
<img width="1166" height="484" alt="image" src="https://github.com/user-attachments/assets/5e641037-5394-49d7-b469-900622c64a2e" />

<img width="1156" height="544" alt="image" src="https://github.com/user-attachments/assets/ae24f24a-cf53-44a1-8d15-ade77a58dbec" />

<img width="1157" height="866" alt="image" src="https://github.com/user-attachments/assets/7d168eb4-17f8-4283-a0f9-54b78c710523" />







# 📌 Foro Hub

Foro Hub es un proyecto backend desarrollado en **Java con Spring Boot**, diseñado como una plataforma de foros donde los usuarios pueden crear, leer y gestionar publicaciones.  
El objetivo es construir una API segura, escalable y lista para producción, aplicando buenas prácticas de arquitectura y documentación.

---

## 🚀 Tecnologías utilizadas
- **Java 17**
- **Spring Boot**
- **Spring Security** con **JWT** para autenticación
- **Hibernate/JPA** para persistencia
- **MySQL / PostgreSQL** como base de datos
- **Flyway** para migraciones
- **Maven** para gestión de dependencias

---

## ⚙️ Funcionalidades principales
- Registro y autenticación de usuarios con **JWT**
- Creación, edición y eliminación de publicaciones
- Gestión de categorías y etiquetas
- Endpoints RESTful documentados
- Validación y manejo de errores consistente
- Seguridad con contraseñas encriptadas (**BCryptPasswordEncoder**)

---

## 📂 Estructura del proyecto
Foro-hub/
├── src/
│   ├── main/
│   │   ├── java/        # Código fuente
│   │   └── resources/   # Configuración y properties
│   └── test/            # Pruebas unitarias e integración
├── pom.xml              # Configuración Maven
├── HELP.md              # Guía inicial de Spring Boot
└── README.md            # Documentación del proyecto


---

## ▶️ Cómo ejecutar el proyecto
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/enzo-isaurralde/Foro-hub.git
   cd Foro-hub

2. Configurar la base de datos en application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/forohub
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password


Ejecutar migraciones con Flyway (automático al levantar la app).

Iniciar la aplicación:

./mvnw spring-boot:run


## 🔑 Endpoints principales
POST /auth/register → Registro de usuario

POST /auth/login → Login y obtención de token JWT

GET /posts → Listar publicaciones

POST /posts → Crear publicación

PUT /posts/{id} → Editar publicación

DELETE /posts/{id} → Eliminar publicación

## 📖 Próximos pasos
Integrar Swagger/OpenAPI para documentación interactiva

Añadir pruebas de integración más completas

## 👨‍💻 Autor

<img width="348" height="396" alt="image" src="https://github.com/user-attachments/assets/cc651b61-6ba6-4950-9897-ef6987175a77" />

Enzo Ariel Isaurralde  
Backend Developer | Java & Spring Boot


