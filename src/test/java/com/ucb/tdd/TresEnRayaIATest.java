package com.ucb.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TresEnRayaIATest {

    private static final String SIN_GANADOR = "No hay ganador";
    private static final String GANA_X = "X es el ganador";
    private static final String GANA_O = "+ es el ganador";

    private TresEnRaya juego;

    @BeforeEach
    void setUp() {
        juego = new TresEnRaya();
    }

    // ---------------------------------------------------------------
    // Requerimiento 1: colocación de piezas en el tablero
    // ---------------------------------------------------------------

    @Test
    void cuandoLaPiezaEstaFueraDelEjeXPorEncimaDelLimiteSeLanzaExcepcion() {
        // Arrange
        int x = 4;
        int y = 1;
        // Act + Assert
        assertThrows(RuntimeException.class, () -> juego.jugar(x, y));
    }

    @Test
    void cuandoLaPiezaEstaFueraDelEjeXPorDebajoDelLimiteSeLanzaExcepcion() {
        // Arrange
        int x = 0;
        int y = 1;
        // Act + Assert
        assertThrows(RuntimeException.class, () -> juego.jugar(x, y));
    }

    @Test
    void cuandoLaPiezaTieneXNegativaSeLanzaExcepcion() {
        // Arrange
        int x = -1;
        int y = 2;
        // Act + Assert
        assertThrows(RuntimeException.class, () -> juego.jugar(x, y));
    }

    @Test
    void cuandoLaPiezaEstaFueraDelEjeYPorEncimaDelLimiteSeLanzaExcepcion() {
        // Arrange
        int x = 1;
        int y = 4;
        // Act + Assert
        assertThrows(RuntimeException.class, () -> juego.jugar(x, y));
    }

    @Test
    void cuandoLaPiezaEstaFueraDelEjeYPorDebajoDelLimiteSeLanzaExcepcion() {
        // Arrange
        int x = 1;
        int y = 0;
        // Act + Assert
        assertThrows(RuntimeException.class, () -> juego.jugar(x, y));
    }

    @Test
    void cuandoLaPiezaTieneYNegativaSeLanzaExcepcion() {
        // Arrange
        int x = 2;
        int y = -5;
        // Act + Assert
        assertThrows(RuntimeException.class, () -> juego.jugar(x, y));
    }

    @Test
    void cuandoAmbasCoordenadasEstanFueraDelTableroSeLanzaExcepcion() {
        // Arrange
        int x = 7;
        int y = 9;
        // Act + Assert
        assertThrows(RuntimeException.class, () -> juego.jugar(x, y));
    }

    @Test
    void cuandoLaPiezaSeColocaEnLasEsquinasDelTableroNoSeLanzaExcepcion() {
        // Arrange + Act + Assert
        assertDoesNotThrow(() -> juego.jugar(1, 1));
        assertDoesNotThrow(() -> juego.jugar(3, 3));
        assertDoesNotThrow(() -> juego.jugar(1, 3));
        assertDoesNotThrow(() -> juego.jugar(3, 1));
    }

    @Test
    void cuandoLaPiezaSeColocaEnUnEspacioOcupadoSeLanzaExcepcion() {
        // Arrange
        juego.jugar(2, 2);
        // Act + Assert
        assertThrows(RuntimeException.class, () -> juego.jugar(2, 2));
    }

    @Test
    void cuandoSeIntentaOcuparUnEspacioOcupadoPorElMismoJugadorSeLanzaExcepcion() {
        // Arrange
        juego.jugar(1, 1); // X
        juego.jugar(2, 2); // +
        // Act + Assert (le toca a X, intenta casilla de X)
        assertThrows(RuntimeException.class, () -> juego.jugar(1, 1));
    }

    @Test
    void cuandoSeIntentaOcuparUnEspacioOcupadoPorElOponenteSeLanzaExcepcion() {
        // Arrange
        juego.jugar(1, 1); // X
        juego.jugar(2, 2); // +
        juego.jugar(3, 3); // X
        // Act + Assert (le toca a +, intenta casilla de X)
        assertThrows(RuntimeException.class, () -> juego.jugar(1, 1));
    }

    @Test
    void cuandoLaJugadaEsInvalidaElTurnoNoAvanza() {
        // Arrange
        assertThrows(RuntimeException.class, () -> juego.jugar(5, 5));
        // Act
        char siguiente = juego.siguienteJugador();
        // Assert
        assertEquals('X', siguiente);
    }

    @Test
    void cuandoLaJugadaEnEspacioOcupadoFallaElTurnoNoAvanza() {
        // Arrange
        juego.jugar(2, 2); // X
        assertThrows(RuntimeException.class, () -> juego.jugar(2, 2));
        // Act
        char siguiente = juego.siguienteJugador();
        // Assert
        assertEquals('+', siguiente);
    }

    @Test
    void cuandoSeColocaUnaPiezaValidaNoHayGanador() {
        // Arrange + Act
        String resultado = juego.jugar(2, 2);
        // Assert
        assertEquals(SIN_GANADOR, resultado);
    }

    // ---------------------------------------------------------------
    // Requerimiento 2: turnos
    // ---------------------------------------------------------------

    @Test
    void elPrimerTurnoEsDeX() {
        // Arrange (juego nuevo)
        // Act
        char siguiente = juego.siguienteJugador();
        // Assert
        assertEquals('X', siguiente);
    }

    @Test
    void siElUltimoTurnoFueDeXEntoncesElSiguienteEsDeMas() {
        // Arrange
        juego.jugar(1, 1); // X
        // Act
        char siguiente = juego.siguienteJugador();
        // Assert
        assertEquals('+', siguiente);
    }

    @Test
    void siElUltimoTurnoFueDeMasEntoncesElSiguienteEsDeX() {
        // Arrange
        juego.jugar(1, 1); // X
        juego.jugar(2, 2); // +
        // Act
        char siguiente = juego.siguienteJugador();
        // Assert
        assertEquals('X', siguiente);
    }

    @Test
    void losTurnosSeAlternanCorrectamenteDuranteVariasJugadas() {
        // Arrange
        juego.jugar(1, 1); // X
        juego.jugar(2, 1); // +
        juego.jugar(3, 1); // X
        // Act
        char siguiente = juego.siguienteJugador();
        // Assert
        assertEquals('+', siguiente);
    }

    @Test
    void consultarElSiguienteJugadorVariasVecesNoAvanzaElTurno() {
        // Arrange
        juego.jugar(1, 1); // X
        // Act
        char primera = juego.siguienteJugador();
        char segunda = juego.siguienteJugador();
        // Assert
        assertEquals('+', primera);
        assertEquals('+', segunda);
    }

    // ---------------------------------------------------------------
    // Requerimiento 3: condiciones de victoria
    // ---------------------------------------------------------------

    @Test
    void siNoSeCumpleLaCondicionDeVictoriaNoHayGanador() {
        // Arrange
        juego.jugar(1, 1); // X
        juego.jugar(2, 2); // +
        // Act
        String resultado = juego.jugar(3, 1); // X
        // Assert
        assertEquals(SIN_GANADOR, resultado);
    }

    @Test
    void siUnaLineaTieneFichasMezcladasNoHayGanador() {
        // Arrange
        juego.jugar(1, 1); // X
        juego.jugar(2, 1); // +
        // Act
        String resultado = juego.jugar(3, 1); // X
        // Assert
        assertEquals(SIN_GANADOR, resultado);
    }

    @Test
    void jugadorXGanaConLaLineaHorizontalInferior() {
        // Arrange
        juego.jugar(1, 1); // X
        juego.jugar(1, 2); // +
        juego.jugar(2, 1); // X
        juego.jugar(2, 2); // +
        // Act
        String resultado = juego.jugar(3, 1); // X
        // Assert
        assertEquals(GANA_X, resultado);
    }

    @Test
    void jugadorXGanaConLaLineaHorizontalCentral() {
        // Arrange
        juego.jugar(1, 2); // X
        juego.jugar(1, 1); // +
        juego.jugar(2, 2); // X
        juego.jugar(2, 1); // +
        // Act
        String resultado = juego.jugar(3, 2); // X
        // Assert
        assertEquals(GANA_X, resultado);
    }

    @Test
    void jugadorXGanaConLaLineaHorizontalSuperior() {
        // Arrange
        juego.jugar(1, 3); // X
        juego.jugar(1, 1); // +
        juego.jugar(2, 3); // X
        juego.jugar(2, 1); // +
        // Act
        String resultado = juego.jugar(3, 3); // X
        // Assert
        assertEquals(GANA_X, resultado);
    }

    @Test
    void jugadorMasGanaConUnaLineaHorizontal() {
        // Arrange
        juego.jugar(1, 2); // X
        juego.jugar(1, 1); // +
        juego.jugar(2, 2); // X
        juego.jugar(2, 1); // +
        juego.jugar(3, 3); // X
        // Act
        String resultado = juego.jugar(3, 1); // +
        // Assert
        assertEquals(GANA_O, resultado);
    }

    @Test
    void jugadorXGanaConLaLineaVerticalIzquierda() {
        // Arrange
        juego.jugar(1, 1); // X
        juego.jugar(2, 1); // +
        juego.jugar(1, 2); // X
        juego.jugar(2, 2); // +
        // Act
        String resultado = juego.jugar(1, 3); // X
        // Assert
        assertEquals(GANA_X, resultado);
    }

    @Test
    void jugadorXGanaConLaLineaVerticalCentral() {
        // Arrange
        juego.jugar(2, 1); // X
        juego.jugar(1, 1); // +
        juego.jugar(2, 2); // X
        juego.jugar(1, 2); // +
        // Act
        String resultado = juego.jugar(2, 3); // X
        // Assert
        assertEquals(GANA_X, resultado);
    }

    @Test
    void jugadorXGanaConLaLineaVerticalDerecha() {
        // Arrange
        juego.jugar(3, 1); // X
        juego.jugar(1, 1); // +
        juego.jugar(3, 2); // X
        juego.jugar(1, 2); // +
        // Act
        String resultado = juego.jugar(3, 3); // X
        // Assert
        assertEquals(GANA_X, resultado);
    }

    @Test
    void jugadorMasGanaConUnaLineaVertical() {
        // Arrange
        juego.jugar(1, 1); // X
        juego.jugar(2, 1); // +
        juego.jugar(1, 2); // X
        juego.jugar(2, 2); // +
        juego.jugar(3, 3); // X
        // Act
        String resultado = juego.jugar(2, 3); // +
        // Assert
        assertEquals(GANA_O, resultado);
    }

    @Test
    void jugadorXGanaConLaDiagonalAscendente() {
        // Arrange
        juego.jugar(1, 1); // X
        juego.jugar(2, 1); // +
        juego.jugar(2, 2); // X
        juego.jugar(3, 2); // +
        // Act
        String resultado = juego.jugar(3, 3); // X
        // Assert
        assertEquals(GANA_X, resultado);
    }

    @Test
    void jugadorXGanaConLaDiagonalDescendente() {
        // Arrange
        juego.jugar(1, 3); // X
        juego.jugar(1, 1); // +
        juego.jugar(2, 2); // X
        juego.jugar(2, 1); // +
        // Act
        String resultado = juego.jugar(3, 1); // X
        // Assert
        assertEquals(GANA_X, resultado);
    }

    @Test
    void jugadorMasGanaConUnaDiagonal() {
        // Arrange
        juego.jugar(1, 2); // X
        juego.jugar(1, 1); // +
        juego.jugar(2, 1); // X
        juego.jugar(2, 2); // +
        juego.jugar(3, 1); // X
        // Act
        String resultado = juego.jugar(3, 3); // +
        // Assert
        assertEquals(GANA_O, resultado);
    }

    @Test
    void dosPiezasEnLineaSinLaTerceraNoDanVictoria() {
        // Arrange
        juego.jugar(1, 1); // X
        juego.jugar(1, 2); // +
        // Act
        String resultado = juego.jugar(2, 1); // X
        // Assert
        assertEquals(SIN_GANADOR, resultado);
    }

    @Test
    void lasPiezasEnUnaLineaDeBordeSinCompletarDiagonalNoDanVictoria() {
        // Arrange
        juego.jugar(1, 1); // X
        juego.jugar(1, 2); // +
        juego.jugar(2, 2); // X
        juego.jugar(3, 2); // +
        // Act
        String resultado = juego.jugar(1, 3); // X
        // Assert
        assertEquals(SIN_GANADOR, resultado);
    }

    @Test
    void tableroLlenoSinLineaCompletaTerminaSinGanador() {
        // Arrange
        // Tablero final (x,y):
        // X + X
        // X + +
        // + X X
        juego.jugar(1, 3); // X
        juego.jugar(2, 3); // +
        juego.jugar(3, 3); // X
        juego.jugar(2, 2); // +
        juego.jugar(1, 2); // X
        juego.jugar(1, 1); // +
        juego.jugar(3, 1); // X
        juego.jugar(3, 2); // +
        // Act
        String resultado = juego.jugar(2, 1); // X
        // Assert
        assertEquals(SIN_GANADOR, resultado);
    }

    @Test
    void ganarEnLaUltimaCasillaDelTableroDevuelveGanador() {
        // Arrange
        // Tablero final (x,y):
        // X + X
        // + X +
        // + X X   -> X completa la diagonal (1,1)-(2,2)-(3,3)
        juego.jugar(1, 3); // X
        juego.jugar(2, 3); // +
        juego.jugar(3, 3); // X
        juego.jugar(1, 2); // +
        juego.jugar(2, 2); // X
        juego.jugar(3, 2); // +
        juego.jugar(2, 1); // X
        juego.jugar(1, 1); // +
        // Act
        String resultado = juego.jugar(3, 1); // X
        // Assert
        assertEquals(GANA_X, resultado);
    }
}
