# Netflix Clone

Repositorio con el backend y frontend del proyecto **Netflix Clone**.

## Estructura del proyecto

Easy-Shop/
├── backend/ # Código backend Spring Boot
├── frontend/ # Código frontend Angular
└── database/ # Scripts y archivos de la base de datos


## Tecnologías usadas

- **Backend:** Java 17, Spring Boot 3.x
- **Frontend:** Angular 18
- **Base de datos:** Oracle (o la que uses)
- **Seguridad:** JWT con Spring Security
- **Control de versiones:** Git y GitHub

## Cómo ejecutar el proyecto

### Backend

Se recomienda abrir la carpeta `backend` en Visual Studio Code o tu IDE favorito con soporte Java/Spring Boot.

Desde el IDE, ejecuta la clase principal para levantar el servidor.

Alternativamente, desde terminal:

```bash
./mvnw spring-boot:run
(O usa el comando equivalente para arrancar Spring Boot si tienes instalado Maven.)

Frontend
Abre la carpeta frontend y corre:

bash
Copiar
Editar
ng serve -o
Esto lanzará el frontend en http://localhost:4200 y abrirá automáticamente el navegador.

Base de datos
Ejecuta los scripts dentro de la carpeta database para crear las tablas y datos iniciales necesarios.

Despliegue
Para desplegar el backend, empaqueta el proyecto con:

bash
Copiar
Editar
./mvnw clean package
Y ejecuta el .jar generado.

El frontend puede ser construido con:

bash
Copiar
Editar
ng build --prod
Y se despliega en cualquier servidor web estático.

Contacto
Si tienes dudas o quieres colaborar, puedes abrir un issue o hacer un pull request.
