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
juego.jugar(x, y)          // x, y entre 1 y 3. Retorna "No hay ganador", "X es el ganador" o "+ es el ganador"
juego.siguienteJugador()   // 'X' o '+'
```

Si la jugada no es válida (fuera del tablero o lugar ocupado) se lanza una `RuntimeException`.

## Clonar el repositorio

```bash
git clone https://github.com/Robert0h2002/tdd-tres-en-raya.git
cd tdd-tres-en-raya
```

## Ejecutar las pruebas

Requisitos: Java 17+ y Maven.

Todas las pruebas (propias + IA, 49 en total):

```bash
mvn clean test
```

Solo mis pruebas (13):

```bash
mvn clean test -Dtest=TresEnRayaTest
```

Solo las pruebas generadas con IA (36):

```bash
mvn clean test -Dtest=TresEnRayaIATest
```

El reporte de cobertura (JaCoCo) queda en `target/site/jacoco/index.html`.

## Prueba de mutantes

El script `docs/mutantes.py` mete un error a la vez en `TresEnRaya.java`
(10 mutantes, por ejemplo quitar la diagonal secundaria o validar mal los
límites), corre las dos suites y muestra cuántas pruebas fallan en cada una.
Sirve para ver qué suite detecta cada error. Al terminar deja el código original.

Requisitos: Python 3 y Maven. Desde la raíz del repositorio:

```bash
python docs/mutantes.py .
```

Si una suite aparece como `n/a` significa que ninguna de sus pruebas falló, es
decir, ese mutante **no** fue detectado.

## Historial TDD

Cada requerimiento se hizo en un commit separado (prueba + código mínimo) y al
final se hizo un commit de refactor sin cambiar el comportamiento. Ver
`git log --oneline`.
