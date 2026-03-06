# 🪪 AppLicencias — Sistema de Gestión de Licencias de Conducir

> Sistema de escritorio para la gestión integral de licencias de conducir de la **Municipalidad de Santa Fe**, desarrollado con Java, Spring Boot y Java Swing.

---

## 📋 Tabla de Contenidos

- [Descripción](#descripción)
- [Características](#características)
- [Tecnologías](#tecnologías)
- [Arquitectura](#arquitectura)
- [Requisitos previos](#requisitos-previos)
- [Configuración e instalación](#configuración-e-instalación)
- [Uso](#uso)
- [Módulos del sistema](#módulos-del-sistema)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Base de datos](#base-de-datos)
- [Contribuir](#contribuir)

---

## Descripción

**AppLicencias** es una aplicación de escritorio desarrollada para digitalizar y agilizar el proceso de emisión, renovación y consulta de licencias de conducir en la Municipalidad de Santa Fe. La aplicación permite gestionar titulares, emitir licencias de distintas clases, registrar operaciones, exportar datos a Excel e imprimir comprobantes, todo desde una interfaz gráfica moderna e intuitiva.

---

## Características

- 🔐 **Autenticación segura** con contraseñas cifradas mediante BCrypt.
- 👤 **Gestión de usuarios** con roles diferenciados (operador y superusuario).
- 📄 **Emisión de licencias** para las clases A, B, C, D, E, F y G.
- 🔄 **Renovación de licencias** con historial de versiones anteriores.
- 📋 **Consulta avanzada de licencias** con filtros por clase, estado y titular.
- 🗂️ **Registro de operaciones** para auditoría de acciones realizadas en el sistema.
- 💵 **Gestión de costos** por clase de licencia.
- 📊 **Exportación a Excel** de listados de licencias.
- 🖨️ **Impresión de licencias** y presupuestos en formato PDF.
- 🎨 **Interfaz moderna** con el tema FlatLaf Light.

---

## Tecnologías

| Tecnología | Versión | Descripción |
|---|---|---|
| Java | 21 | Lenguaje principal |
| Spring Boot | 3.5.0 | Framework de aplicación |
| Spring Data JPA | — | Capa de persistencia |
| Hibernate | — | ORM |
| MySQL | 8.x | Motor de base de datos |
| Java Swing | — | Interfaz gráfica de escritorio |
| FlatLaf | 3.4 | Tema moderno para Swing |
| Apache POI | 5.2.5 | Generación de archivos Excel |
| MapStruct | 1.6.3 | Mapeo entre DTOs y entidades |
| Lombok | — | Reducción de código boilerplate |
| Spring Security Crypto | — | Cifrado de contraseñas (BCrypt) |
| jCalendar | 1.4 | Componente de selección de fechas |
| Maven | — | Gestión de dependencias y build |

---

## Arquitectura

El proyecto sigue una arquitectura en capas MVC adaptada para aplicaciones de escritorio:

```
┌─────────────────────────────────────────────┐
│              Capa de Presentación           │
│        (Swing Views + Controllers)          │
├─────────────────────────────────────────────┤
│              Capa de Servicios              │
│           (Service / ServiceImpl)           │
├─────────────────────────────────────────────┤
│              Capa de Persistencia           │
│         (Repositories + JPA/Hibernate)      │
├─────────────────────────────────────────────┤
│              Base de Datos                  │
│                  (MySQL)                    │
└─────────────────────────────────────────────┘
```

- **Módulos**: Cada funcionalidad está encapsulada en su propio paquete con su `View` y `Controller`.
- **Inyección de dependencias**: Gestionada por el contenedor de Spring Boot.
- **Sesión**: Manejada mediante `SessionController` y `UserSession` como componentes de Spring.
- **Validación**: Mediante la clase `ValidationResult` con soporte de múltiples errores por operación.
- **Mapeo**: DTOs mapeados a entidades con MapStruct.

---

## Requisitos previos

Antes de ejecutar la aplicación, asegúrate de tener instalado:

- **Java JDK 21** o superior → [Descargar](https://adoptium.net/)
- **MySQL 8.x** → [Descargar](https://dev.mysql.com/downloads/mysql/)
- **Maven 3.8+** → [Descargar](https://maven.apache.org/download.cgi) *(o usar el wrapper incluido)*

---

## Configuración e instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/KarimServin/appLicencias.git
cd appLicencias
```

### 2. Crear la base de datos

Importa el esquema incluido en el proyecto:

```bash
mysql -u root -p < applicenciasdb.sql
```

Esto creará la base de datos `applicenciasdb` con todas las tablas y datos iniciales de prueba.

### 3. Configurar las credenciales de la base de datos

Edita el archivo `src/main/resources/application.properties` y ajusta las credenciales según tu entorno:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/applicenciasdb
spring.datasource.username=root
spring.datasource.password=TU_CONTRASEÑA
```

### 4. Compilar y ejecutar

Con el wrapper de Maven incluido:

```bash
# En Linux/macOS
./mvnw spring-boot:run

# En Windows
mvnw.cmd spring-boot:run
```

O bien, compilar el JAR y ejecutarlo:

```bash
./mvnw clean package -DskipTests
java -jar target/appLicencias-0.0.1-SNAPSHOT.jar
```

---

## Uso

Al iniciar la aplicación, se mostrará la **pantalla de login**. Ingresa con las credenciales de un usuario registrado en la base de datos.

- **Superusuario**: tiene acceso a todos los módulos, incluyendo gestión de usuarios y costos.
- **Operador**: tiene acceso limitado a las funciones operativas (emitir licencias, consultas, etc.).

---

## Módulos del sistema

| Módulo | Descripción |
|---|---|
| **Login** | Autenticación de usuarios con validación de credenciales y estado de cuenta. |
| **Menú Principal** | Panel central con acceso a todos los módulos según el rol del usuario. |
| **Alta de Titular** | Registro de nuevos titulares con datos personales, grupo sanguíneo, donante, etc. |
| **Modificar Titular** | Actualización de datos de un titular existente. |
| **Emitir Licencia** | Emisión de nuevas licencias seleccionando clase, titular y período de vigencia. |
| **Emitir Copia de Licencia** | Generación de una copia de una licencia previamente emitida. |
| **Consultar Licencias** | Búsqueda y filtrado de licencias con exportación a Excel. |
| **Consultar Operaciones** | Historial de operaciones realizadas en el sistema para auditoría. |
| **Alta de Usuario** | Creación de nuevos usuarios del sistema (solo superusuario). |
| **Gestionar Usuarios** | Listado, edición y gestión del estado de usuarios (solo superusuario). |
| **Gestionar Costos** | Configuración del costo asociado a cada clase de licencia (solo superusuario). |

---

## Estructura del proyecto

```
appLicencias/
├── src/
│   ├── main/
│   │   ├── java/com/municipalidad/licencias/appLicencias/
│   │   │   ├── auth/              # Autenticación
│   │   │   ├── dto/               # Data Transfer Objects
│   │   │   ├── entities/          # Entidades JPA
│   │   │   ├── exception/         # Excepciones personalizadas
│   │   │   ├── factory/           # Fábrica de controladores
│   │   │   ├── mapper/            # Mappers MapStruct
│   │   │   ├── modules/           # Módulos de la aplicación
│   │   │   │   ├── altatitular/
│   │   │   │   ├── altausuario/
│   │   │   │   ├── consultarlicencias/
│   │   │   │   ├── consultaroperaciones/
│   │   │   │   ├── emitircopialicencia/
│   │   │   │   ├── emitirlicencia/
│   │   │   │   ├── gestionarcostos/
│   │   │   │   ├── gestionarusuarios/
│   │   │   │   ├── login/
│   │   │   │   ├── menu/
│   │   │   │   └── modificartitular/
│   │   │   ├── repository/        # Repositorios Spring Data
│   │   │   ├── security/          # Cifrado de contraseñas
│   │   │   ├── service/           # Interfaces de servicios
│   │   │   │   └── serviceImpl/   # Implementaciones
│   │   │   ├── session/           # Gestión de sesión de usuario
│   │   │   ├── styles/            # Configuración de Look & Feel
│   │   │   ├── validation/        # Lógica de validación
│   │   │   ├── viewforms/         # Formularios de impresión (PDF)
│   │   │   ├── AppLicencias.java  # Clase principal
│   │   │   └── BootModule.java    # Inicio de la UI tras arrancar Spring
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/                  # Tests unitarios e integración
├── applicenciasdb.sql             # Script de base de datos
├── pom.xml                        # Configuración Maven
└── README.md
```

---

## Base de datos

La base de datos `applicenciasdb` contiene las siguientes tablas principales:

| Tabla | Descripción |
|---|---|
| `titular` | Datos de los titulares de licencias (DNI, nombre, nacimiento, sangre, etc.) |
| `licencia` | Licencias emitidas, con clase, vigencia y referencia al titular y usuario. |
| `usuario` | Usuarios del sistema con rol y estado. |
| `comprobante` | Comprobantes de pago asociados a la emisión de licencias. |
| `costo_clase_licencia` | Costo vigente por cada clase de licencia. |
| `registro_operacion` | Auditoría de operaciones realizadas en el sistema. |
| `tipo_sangre` | Catálogo de tipos de sangre disponibles. |

**Clases de licencia soportadas:**

| Clase | Tipo de vehículo habilitado |
|---|---|
| A | Ciclomotores y motocicletas |
| B | Automóviles, camionetas y similares |
| C | Camiones sin acoplado |
| D | Vehículos del servicio de transporte de pasajeros |
| E | Camiones con acoplado, semirremolque y maquinaria especial |
| F | Vehículos especiales (no comprendidos en otras clases) |
| G | Maquinaria especial no agrícola |

---

## Contribuir

Las contribuciones son bienvenidas. Por favor, seguí estos pasos:

1. Hacé un fork del repositorio.
2. Creá una rama para tu funcionalidad: `git checkout -b feature/nueva-funcionalidad`.
3. Realizá tus cambios y confirmalos: `git commit -m 'Agrega nueva funcionalidad'`.
4. Enviá la rama: `git push origin feature/nueva-funcionalidad`.
5. Abrí un Pull Request describiendo los cambios realizados.

---

<p align="center">
  Desarrollado para la <strong>Municipalidad de Santa Fe</strong>
</p>
