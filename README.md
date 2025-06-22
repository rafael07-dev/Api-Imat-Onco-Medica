# 🏥 Oncomedica Inventory Management System

> Sistema de gestión de inventario y mantenimiento de equipos médicos  
> Desarrollado con **Spring Boot** siguiendo los principios de **Arquitectura Limpia**, **SOLID**, y buenas prácticas de desarrollo profesional.

---

## 📦 Características principales

✅ Gestión de equipos médicos  
✅ Programación y seguimiento de mantenimientos  
✅ Registro de responsables técnicos  
✅ Notificación automática por correo al asignar mantenimientos  
✅ API REST estructurada por casos de uso  
✅ Separación clara de capas (domain, application, infrastructure)

---

## 🧠 Arquitectura limpia

Este proyecto sigue el enfoque de **Clean Architecture**:

```
└── com.imat.oncomedica.inventory_management
    ├── domain                 → Entidades y contratos del dominio
    ├── application            → Casos de uso (orquestación de lógica)
    ├── infrastructure         → Repositorios, notificaciones, configuración
    └── config                 → Beans de configuración (ensamblaje manual)
```

> 💡 El dominio no depende de Spring ni de ninguna tecnología externa.  
> El caso de uso se inyecta en el controlador.  
> La lógica de negocio no está en los controladores ni en los servicios típicos.

---

## ⚙️ Tecnologías utilizadas

- Java 17+
- Spring Boot 3
- Spring Data JPA
- MySQL o PostgreSQL
- JavaMailSender (para notificaciones)
- MapStruct (para mapeo DTO-Entidad)
- Lombok
- Maven

---

## 🛠️ Endpoints principales (REST API)

| Método | Ruta                        | Descripción                          |
|--------|-----------------------------|--------------------------------------|
| GET    | `/api/equipment`            | Lista todos los equipos              |
| POST   | `/api/maintenances`         | Crea un mantenimiento y notifica     |
| GET    | `/api/maintenances/{id}`    | Obtiene un mantenimiento por ID      |
| PUT    | `/api/maintenances/{id}`    | Actualiza un mantenimiento existente |
| DELETE | `/api/maintenances/{id}`    | Elimina un mantenimiento             |

---

## ✉️ Notificaciones por correo

Cuando se asigna un mantenimiento a un técnico responsable, el sistema le envía automáticamente un correo con los detalles.

🔧 Configura tu cuenta SMTP en `application.properties`:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=tu_correo@gmail.com
spring.mail.password=tu_contraseña_generada
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

> Usa una contraseña de aplicación si trabajas con Gmail.

---

## 🚀 Cómo ejecutar el proyecto

```bash
# Clonar el repositorio
git clone https://github.com/tuusuario/oncomedica-inventory.git
cd oncomedica-inventory

# Configurar application.properties con tu base de datos y correo

# Compilar y ejecutar
./mvnw spring-boot:run
```

---

## 📸 Capturas


---

## 🧪 Pruebas

- Los casos de uso se pueden probar de forma aislada.
- Se recomienda usar JUnit + Mockito para testear `CreateMaintenanceUseCase`.

---

## 📄 Licencia

Proyecto académico con fines formativos.  
Distribuido bajo licencia MIT.

---

## 👨‍💻 Autor

Desarrollado por **Deiner Lares**  
Estudiante de Análisis y Desarrollo de Software - SENA  
Apasionado por la arquitectura limpia y el código profesional.