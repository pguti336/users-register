# User Register

Esta aplicación Spring Boot expone un servicio RESTful para la creación de usuarios. Incluye un endpoint POST que recibe la información del usuario a registrar, utilizando una base de datos H2 para persistir los datos.

## Tecnologías

- Spring Boot
- Spring Data JPA
- Spring Security
- H2 Database (o tu base de datos preferida)

## Modelo de datos

### Tabla `usuarios`

| Columna   | Tipo         | Descripción            |
|-----------|--------------|------------------------|
| id        | UUID         | Clave primaria          |
| email     | VARCHAR(256) | Correo electrónico     |
| password  | VARCHAR(256) | Contraseña             |
| created   | DATETIME     | Fecha de creación      |
| modified  | DATETIME     | Fecha de modificación  |
| active    | BOOLEAN      | Indicador de activo    |

### Tabla `telefonos`

| Columna      | Tipo         | Descripción                      |
|--------------|--------------|----------------------------------|
| id           | BIGINT       | Clave primaria autoincremental   |
| number       | VARCHAR(9)   | Número de teléfono               |
| country_code | VARCHAR(3)   | Código de país                   |
| city_code    | VARCHAR(3)   | Código de ciudad                 |
| user_id      | UUID         | Clave foránea a usuarios (id)    |

#### Relaciones:

- `telefonos.user_id` -> `usuarios.id` (ON DELETE CASCADE)

## Configuración
```bash
git clone https://github.com/pguti336/register
cd register
export JAVA_HOME=/path/to/java/installation
./gradlew bootRun
```

La aplicación se ejecutará en http://localhost:8080

## Endpoint de creación de Usuarios
Para crear un usuario, hacer un request POST a /api/users con body en formato JSON que contenga los datos del usuario.
```
POST /api/users

{
    "name": "Test User",
    "email": "testuser@example.om",
    "password": "P4s$word",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        },  {
            "number": "8888888",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```

## Endpoint de consulta de Usuarios
Para obtener un usuario dado su UUID, hacer un request GET a /internal/users/{uuid}
```
GET /internal/users/{uuid}
```


## Respuesta Exitosa
Si la creación es exitosa, se recibe una respuesta con HTTP status 201 Created y los datos del usuario creado.
Ejemplo:
```
{
    "id": "53baff4f-a5b3-4663-917c-3404448dd5cc",
    "created": "2024-07-15T15:20:35.208112Z",
    "modified": "2024-07-15T15:20:35.210190Z",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QHVzMWVyLmNvbSIsImlhdCI6MTcyMTA1NjgzNSwiZXhwIjoxNzIxMDYwNDM1fQ.2XKXfC85jJxTW3B5jTBiUNBIbuvp2Lv_ccB-VQGCeek",
    "last_login": "2024-07-15T15:20:35.207340329Z",
    "is_active": true
}
```
Por otra parte, en el caso del endpoint GET, se recibe el mismo cuerpo de respuesta con HTTP status 200 OK.


## Endpoint de login
```
POST /api/login

{
    "email": "test@user.com",
    "password": "P4s$word"
}
```
La respuesta exitosa de este endpoint posee la siguiente estructura y HTTP status 200 OK:

```
{
    "email": "test@test.com",
    "authenticated": true
}
```



## Documentación
Para acceder a documentación Swagger navegar a:
http://localhost:8080/swagger-ui/index.html

E ingresar las credenciales del usuario creado.

## Curls de prueba
Creación de usuarios
```
curl --location '0.0.0.0:8080/api/users --data-raw '{
    "name": "Test User",
    "email": "test@user.com",
    "password": "P4s$word",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "countrycode": "57"
        },  {
            "number": "8888888",el mismo cuerpo de resp
            "citycode": "1",
            "countrycode": "57"
        }
    ]
}'
```

Consulta de usuario dado su UUID
```
curl --location '0.0.0.0:8080/internal/users/1c1375a0-3c30-4825-9207-4b9b37981329'
--header 'Authorization: Bearer paste_your_token' 
```
Se debe ingresar el token obtenido en la respuesta anterior ya que este endpoint está protegido, es decir se debe consultar estando autenticado.

Adicionalmente, se deja disponible el endpoint de login

```
curl --location '0.0.0.0:8080/api/login' --data-raw '{
    "email": "test@user.com",
    "password": "P4s$word"
}'
```

## Diagrama de Funcionamiento del Endpoint de Creación de Usuarios

El siguiente diagrama describe el flujo básico para la creación de usuarios a través de un endpoint en una aplicación Spring Boot.

```plaintext
Cliente             Controller     Service       Repository      Base de Datos
  |                     |              |              |                |
  |  POST /api/usuarios |              |              |                |
  |-------------------->|              |              |                |
  |                     |              |              |                |
  |    Body JSON        |              |              |                |
  | (name, email,       |              |              |                |
  |password, phones)                   |              |                |
  |---------------->    |              |              |                |
  |                     |              |              |                |
  |                     |Validación de |              |                |
  |                     |  datos y     |              |                |
  |                     |formato       |              |                |
  |                     |              |              |                |
  |                     |------------->|              |                |
  |                     |              |              |                |
  |                     |   Creación   |              |                |
  |                     |   de Usuario |              |                |
  |                     |------------->|              |                |
  |                     |              |              |                |
  |                     |              |Guardar en    |                |
  |                     |              | Base de Datos|                |
  |                     |              |------------->|                |
  |                     |              |              |                |
  |                     |              |              |                |
  |                     |              |              |   Operación    |
  |                     |              |              |   Exitosa      |
  |                     |              |              |<---------------|
  |                     |              |              |                |
  | Respuesta HTTP      |              |              |                |
  |  201 Created        |              |              |                |
  |<------------------- |              |              |                |
  |                     |              |              |                |
