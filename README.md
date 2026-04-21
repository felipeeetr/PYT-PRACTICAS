# Juego Matemático

## Descripción
Este proyecto es un juego inspirado en Wordle en el que el jugador debe adivinar una ecuación matemática oculta. En cada intento, el jugador propone una ecuación y el sistema proporciona retroalimentación mediante colores indicando qué números u operadores están en la posición correcta, cuáles existen pero están en otra posición y cuáles no pertenecen a la ecuación. El objetivo es descubrir la ecuación correcta antes de alcanzar el límite de intentos.

## Arquitectura del proyecto

El proyecto sigue el patrón MVC (Modelo-Vista-Controlador) y está gestionado con Maven.

## Estructura principal

Controller: src/main/java/com/juegomatematico/controller/
Gestiona la interacción entre la vista y el modelo, valida los intentos del jugador y actualiza la interfaz.

Model: src/main/java/com/juegomatematico/model/
Contiene la lógica de negocio, incluyendo la generación de ecuaciones aleatorias y la verificación de intentos.

View: src/main/java/com/juegomatematico/view/
Maneja la interfaz gráfica con JavaFX, mostrando los intentos y la retroalimentación de colores.

Resources: src/main/resources/
Archivos de configuración y recursos del proyecto.

Tests: src/test/java/com/juegomatematico/model/
Pruebas unitarias con JUnit para validar el generador de ecuaciones y la lógica del juego.

Maven: pom.xml
Gestión de dependencias y configuración del proyecto.  
 

## Estructura de la ecuación
Reglas:
- Operadores permitidos: +, -, *
- Números entre 1 y 12
- La ecuación debe estar organizada en una estructura fija: _ + _ * _ - = x, donde _ son los números que va a ingresar el usuario y x es el resultado a adivinar.

## Funciones actuales del proyecto

En esta versión, el proyecto es capaz de generar una ecuación aleatoria que respete las reglas del juego propuesto, esto en el archivo GeneradorEcuaciones.java


### Pruebas unitarias

Se implementaron pruebas unitarias con JUnit para validar el generador de ecuaciones.
Las pruebas verifican que:

* Se genere correctamente una ecuación.
* El resultado matemático sea correcto según la operación `A + B * C - D`.
* Los números generados estén dentro del rango permitido (1–12).

