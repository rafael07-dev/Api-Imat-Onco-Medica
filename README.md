# 🏥 OncoMédica – Sistema de Gestión de Mantenimientos e Inventario

**OncoMédica** es una aplicación profesional diseñada para clínicas, enfocada en la **gestión de inventarios de equipos médicos** y **mantenimientos técnicos**, con funcionalidades en tiempo real y principios sólidos de arquitectura limpia y buenas prácticas.

---

## 🚀 Funcionalidades principales

- 📋 Gestión de inventario de equipos médicos.
- 🛠️ Registro y asignación de mantenimientos (preventivos y correctivos).
- 📁 Subida de imágenes de equipos y mantenimientos.
- ✍️ Firma electrónica para órdenes de mantenimiento.
- 📑 Generación automática de órdenes de mantenimiento.
- 🔔 **Notificaciones en tiempo real** cuando un mantenimiento es asignado o actualizado.
- 📊 Reportes detallados de mantenimientos.
- 👥 Gestión de personal técnico.
- 🧑‍⚕️ Roles: técnico, administrador y futuros módulos para firmas responsables.
- ✅ Cumplimiento con buenas prácticas: SOLID, arquitectura limpia, separación por capas.

---

## 🧩 Arquitectura

La aplicación está organizada siguiendo principios de **Arquitectura Limpia** y **DDD (Domain-Driven Design)**:

- `domain/`: Entidades, interfaces y reglas del negocio.
- `application/`: Casos de uso (por ejemplo: `CreateMaintenanceUseCase`, `UploadStaffSignatureUseCase`, etc).
- `infrastructure/`: Implementaciones de persistencia y almacenamiento de archivos.
- `web/`: Controladores HTTP, configuraciones de rutas y eventos en tiempo real.

---

## 🖼️ Gestión de Archivos y Firmas

El sistema permite almacenar archivos relacionados con:

- **Imágenes de equipos** en `uploads/images/equipment/{id}/`.
- **Imágenes de mantenimientos** en `uploads/images/maintenance/{id}/`.
- **Firmas de técnicos y administradores**:
  - Técnicos: `uploads/images/signatures/staff/{id}/`
  - Administradores: `uploads/images/signatures/admin/{id}/`

Estos archivos son validados por tipo (`.jpg`, `.jpeg`, `.png`, `.webp`) y organizados para un acceso rápido y seguro.

---

## ⚡ Funcionalidad en Tiempo Real (WebSockets)

El sistema utiliza WebSockets para ofrecer actualizaciones **en tiempo real**, mejorando la eficiencia operativa del personal técnico y administrativo. Actualmente, las notificaciones cubren:

- ✅ **Asignación de un mantenimiento**: El técnico es notificado instantáneamente.
- 🔄 **Cambio de estado del mantenimiento**: “En progreso”, “pausado” o “completado”.
- 📥 **Nueva orden de trabajo generada**: Vinculada a un equipo o técnico específico.
- 🔔 **Alertas de coordinación**: Preparado para escalar a recordatorios, cancelaciones, etc.

---

## 🛠️ Tecnologías utilizadas

- **Backend**: Java 17 + Spring Boot
- **Web**: Spring Web + WebSockets
- **Persistencia**: Spring Data JPA + Hibernate + MySQL
- **Arquitectura**: Hexagonal / Limpia
- **DTO y Mappers**: MapStruct o manual
- **Gestión de archivos**: `java.nio.file` + `MultipartFile`
- **Notificaciones en tiempo real**: WebSocket API
- **Validaciones**: Custom exceptions y validación de extensiones

---

## 📦 Estructura modular

```bash
src/
├── domain/
│   ├── entity/
│   ├── exception/
│   └── service/
├── application/
│   ├── dto/
│   ├── mapper/
│   └── usecase/
├── infrastructure/
│   ├── repository/
│   └── storage/
├── web/
│   ├── controller/
│   └── websocket/
```

---

## 🔒 Buenas prácticas

- ✅ Separación de capas (Application, Domain, Infrastructure, Web)
- ✅ Inversión de dependencias (Interfaces en dominio, implementación en infraestructura)
- ✅ Uso de casos de uso para lógica de negocio (evitando Services anémicos)
- ✅ Validación temprana de archivos y parámetros
- ✅ Diseño orientado al crecimiento y extensibilidad

---

## 📈 En desarrollo...

- Módulo de autenticación con JWT.
- Sistema de permisos por rol.
- Dashboard con estadísticas de mantenimientos.
- Exportación de reportes en PDF.
- Firma digital vinculada a órdenes con fecha y hora: `"Firmado por [nombre] el [fecha]"`.
- Sistema de notificaciones por correo electrónico.