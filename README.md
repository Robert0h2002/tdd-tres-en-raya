# Kata Tres en Raya con TDD

![CI](https://github.com/Robert0h2002/tdd-tres-en-raya/actions/workflows/maven.yml/badge.svg)

Práctica de la materia **Gestión de Calidad de Sistemas**: resolver la kata del
Tres en Raya aplicando *Test Driven Development* (Red → Green → Refactor) y
ejecutar las pruebas automáticamente con **CI en GitHub Actions**.

## Requerimientos de la kata

1. Una pieza puede estar colocada en un espacio de un tablero 3x3
   (excepción si está fuera del eje X, fuera del eje Y o en un lugar ocupado).
2. Debe existir una forma de saber qué jugador juega después
   (empieza `X`, luego `+`, luego `X`...).
3. Un jugador gana si conecta una línea horizontal, vertical o diagonal.

## Estructura

```
src/main/java/com/ucb/tdd/TresEnRaya.java        -> programa
src/test/java/com/ucb/tdd/TresEnRayaTest.java    -> mis pruebas unitarias (13)
src/test/java/com/ucb/tdd/TresEnRayaIATest.java  -> pruebas generadas con IA (36)
.github/workflows/maven.yml                      -> pipeline de CI
docs/mutantes.py                                 -> script para la prueba de mutantes
```

## Uso

```
juego.jugar(x, y)          // x, y entre 1 y 3. Retorna "No hay ganador" o "X es el ganador"
juego.siguienteJugador()   // 'X' o '+'
```

## Ejecutar las pruebas

Requisitos: Java 17+ y Maven.

```bash
mvn clean test
```

El reporte de cobertura (JaCoCo) queda en `target/site/jacoco/index.html`.

## Historial TDD

Cada requerimiento se hizo en un commit separado (prueba + código mínimo) y al
final se hizo un commit de refactor sin cambiar el comportamiento. Ver
`git log --oneline`.
