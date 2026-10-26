# Prompt usado para generar las pruebas unitarias con IA

## Prompt 1 (principal)

```
Actúa como un desarrollador Java con experiencia en TDD (Test Driven Development).

Necesito que escribas SOLO las pruebas unitarias (todavía no el código) para la
kata "Tres en Raya" (tic-tac-toe). Usa Java 17 y JUnit 5 (org.junit.jupiter).

Requerimientos de la kata:

Requerimiento 1: Una pieza puede estar colocada en un espacio de un tablero 3x3.
  - Cuando una pieza está fuera del eje X, se debe lanzar una excepción.
  - Cuando una pieza está fuera del eje Y, se debe lanzar una excepción.
  - Cuando una pieza esté en un lugar ya ocupado, se debe lanzar una excepción.

Requerimiento 2: Debe existir una forma de determinar qué jugador juega después.
  - El primer turno siempre debe ser jugado por 'X'.
  - Si el último turno fue jugado por 'X', entonces el siguiente turno es para '+'.
  - Si el último turno fue jugado por '+', entonces el siguiente turno es para 'X'.

Requerimiento 3: Un jugador gana si es el primero en conectar una línea de un lado
a otro, o de una esquina a otra.
  - Si no se cumple la condición de victoria, entonces no hay ganador.
  - Un jugador gana cuando toda una línea horizontal es ocupada por sus piezas.
  - Un jugador gana cuando toda una línea vertical es ocupada por sus piezas.
  - Un jugador gana cuando toda una línea diagonal es ocupada por sus piezas.

La clase a probar se llama TresEnRaya, está en el paquete com.ucb.tdd y tiene
esta API pública:

  public class TresEnRaya {
      // x = eje horizontal, y = eje vertical, ambos van de 1 a 3
      // Lanza RuntimeException si la jugada no es válida.
      // Retorna "No hay ganador", "X es el ganador" o "+ es el ganador".
      public String jugar(int x, int y);

      // Retorna el jugador al que le toca el siguiente turno ('X' o '+').
      public char siguienteJugador();
  }

Condiciones:
  - Una clase de prueba llamada TresEnRayaIATest en el paquete com.ucb.tdd.
  - Nombres de métodos de prueba descriptivos en español.
  - Sigue el patrón Arrange-Act-Assert.
  - Cubre todos los escenarios de los requerimientos y agrega los casos borde que
    consideres importantes.
  - Devuelve solo el código Java de la clase de prueba, sin explicación.
```

## Notas

- La API (firma de los métodos y mensajes) se incluyó en el prompt para que las
  pruebas generadas compilen contra la misma implementación y así poder
  compararlas de forma justa con las pruebas propias.
- El resultado generado está en [`TresEnRayaIATest.java`](../src/test/java/com/ucb/tdd/TresEnRayaIATest.java) (sin modificaciones, se ejecuta junto con mis pruebas en el CI).
