# Ooodle Game

## Descripción

Ooodle Game es un juego matemático inspirado en Wordle en el que el jugador debe descubrir una combinación oculta de números. Cada intento consiste en seleccionar cuatro números únicos entre 1 y 12 para formar una ecuación matemática bajo una estructura fija. El sistema evalúa el intento y proporciona retroalimentación visual mediante colores, indicando qué números están en la posición correcta, cuáles existen pero están ubicados en otra posición y cuáles no pertenecen a la solución.

El objetivo es encontrar la combinación correcta antes de agotar los intentos disponibles.

---

## Características principales

- Generación aleatoria de ecuaciones matemáticas.
- Sistema de retroalimentación por colores tipo Wordle:
  - 🟩 Verde: número correcto en la posición correcta.
  - 🟨 Amarillo: número existente, pero en otra posición.
  - ⬜ Gris: número no presente en la solución.
- Restricción de números repetidos.
- Validación de intentos.
- Sistema de puntajes según el número de intentos utilizados.
- Inicio de sesión opcional con nombre de jugador.
- Persistencia de puntajes mediante base de datos MySQL.
- Tabla de ranking de jugadores.
- Interfaz gráfica desarrollada con JavaFX.
- Pruebas unitarias implementadas con JUnit.

---

## Arquitectura del proyecto

El proyecto sigue una arquitectura basada en separación de responsabilidades inspirada en MVC (Modelo–Vista–Controlador), complementada con una capa de servicios para la persistencia de datos.

### Modelo (`model`)
Contiene la lógica de negocio del juego:

- **Ecuacion.java**  
  Genera la solución aleatoria, valida resultados y realiza los cálculos matemáticos.

- **Partida.java**  
  Gestiona el estado del juego, intentos, validaciones, puntuación y retroalimentación de colores.

- **Jugador.java**  
  Representa la información del jugador y sus estadísticas.

### Vista (`view`)
Desarrollada con JavaFX y archivos FXML.

Incluye:

- Pantalla de inicio.
- Pantalla principal del juego.
- Ventana de instrucciones.
- Tabla de puntajes.
- Hoja de estilos CSS.

### Controlador (`controller`)
Gestiona la interacción entre interfaz y lógica del juego.

- **InicioController.java**  
  Maneja el ingreso de jugadores, acceso al juego, instrucciones y ranking.

- **JuegoController.java**  
  Controla el flujo principal de la partida, validación de números, tablero y resultados.

- **PuntajesController.java**  
  Administra la visualización del ranking de jugadores.

### Servicios (`services`)
Encargados de la persistencia de datos.

- **ConexionBD.java**  
  Gestiona la conexión con MySQL y crea automáticamente la base de datos y tablas necesarias.

- **JugadorRepository.java**  
  Realiza operaciones CRUD relacionadas con jugadores y puntajes.

### Tests (`src/test`)
Pruebas unitarias con JUnit para validar la lógica del sistema.

---

## Estructura del proyecto

```text
src/
├── main/
│   ├── java/
│   │   └── co/edu/poli/pyt/
│   │       ├── controller/
│   │       ├── model/
│   │       ├── services/
│   │       └── App.java
│   │
│   └── resources/
│       └── co/edu/poli/pyt/view/
│           ├── inicio.fxml
│           ├── juego.fxml
│           ├── instrucciones.fxml
│           ├── puntajes.fxml
│           └── style.css
│
└── test/
    └── java/
        └── co/edu/poli/pyt/model/
```

---

## Reglas del juego

### Estructura matemática

La ecuación sigue siempre el formato:

```text
A * B - C + D
```

Donde:

- `A`, `B`, `C` y `D` son números enteros únicos.
- Los números deben estar entre **1 y 12**.
- No se permiten números repetidos.
- El jugador debe encontrar la combinación correcta de números que produzca el resultado oculto.

### Intentos

El jugador dispone de un número limitado de intentos para descubrir la solución.

Después de cada intento, el juego muestra colores de retroalimentación sobre cada número ingresado.

---

## Sistema de puntuación

La puntuación depende del intento en el que el jugador gane:

| Intento | Puntos |
|----------|---------|
| 1 | 10 |
| 2 | 8 |
| 3 | 6 |
| 4 | 4 |
| 5 | 2 |
| 6 | 1 |

Los puntajes se almacenan automáticamente para jugadores registrados.

---

## Base de datos

El proyecto utiliza **MySQL** para almacenar jugadores y puntajes.

La aplicación crea automáticamente:

- La base de datos (`ooodle_game`)
- La tabla `jugadores`

Estructura principal:

```sql
jugadores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(80) UNIQUE NOT NULL,
    puntaje_total INT NOT NULL DEFAULT 0,
    partidas_ganadas INT NOT NULL DEFAULT 0
)
```

### Variables de entorno soportadas

La conexión puede configurarse mediante variables de entorno:

| Variable | Descripción |
|----------|-------------|
| `DB_NAME` | Nombre de la base de datos |
| `DB_SERVER_URL` | URL del servidor MySQL |
| `DB_URL` | URL completa de conexión |
| `DB_USER` | Usuario de MySQL |
| `DB_PASSWORD` | Contraseña |

---

## Tecnologías utilizadas

- Java 17+
- JavaFX
- Maven
- MySQL
- JDBC
- JUnit 5

---

## Pruebas unitarias

El proyecto incluye pruebas unitarias para verificar:

### `EcuacionTest`
- Cálculo correcto de la ecuación matemática.
- Verificación de resultados correctos e incorrectos.
- Generación de números únicos.
- Validación de rango permitido (1–12).

### `PartidaTest`
- Validación del sistema de puntuación.
- Asignación correcta de puntos según el intento.
- Verificación del estado de la partida.

---

## Ejecución del proyecto

1. Clonar el repositorio:

```bash
git clone <repositorio>
```

2. Entrar al proyecto:

```bash
cd proyecto
```

3. Ejecutar con Maven:

```bash
mvn javafx:run
```

---

## Autores

Proyecto desarrollado como parte de una práctica académica realizado por Edwin Felipe Torres Rodríguez y Thomas Santiago Caballero Giraldo.
