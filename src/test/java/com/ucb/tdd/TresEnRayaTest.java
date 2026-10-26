package com.ucb.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TresEnRayaTest {

    private TresEnRaya juego;

    @BeforeEach
    void setUp() {
        juego = new TresEnRaya();
    }

    // ---------- Requerimiento 1: colocar piezas en un tablero 3x3 ----------

    @Test
    void cuandoPiezaFueraDelEjeXEntoncesLanzaExcepcion() {
        RuntimeException e = assertThrows(RuntimeException.class, () -> juego.jugar(4, 2));
        assertEquals("X esta fuera del tablero", e.getMessage());
    }

    @Test
    void cuandoPiezaFueraDelEjeYEntoncesLanzaExcepcion() {
        RuntimeException e = assertThrows(RuntimeException.class, () -> juego.jugar(2, 4));
        assertEquals("Y esta fuera del tablero", e.getMessage());
    }

    @Test
    void cuandoLugarOcupadoEntoncesLanzaExcepcion() {
        juego.jugar(2, 1);
        RuntimeException e = assertThrows(RuntimeException.class, () -> juego.jugar(2, 1));
        assertEquals("El lugar ya esta ocupado", e.getMessage());
    }

    @Test
    void cuandoCoordenadaEsCeroEntoncesLanzaExcepcion() {
        assertThrows(RuntimeException.class, () -> juego.jugar(0, 1));
        assertThrows(RuntimeException.class, () -> juego.jugar(1, 0));
    }

    @Test
    void cuandoPiezaEnEsquinaDelTableroNoLanzaExcepcion() {
        assertDoesNotThrow(() -> juego.jugar(3, 3));
    }

    // ---------- Requerimiento 2: saber que jugador juega despues ----------

    @Test
    void primerTurnoSiempreEsDeX() {
        assertEquals('X', juego.siguienteJugador());
    }

    @Test
    void siUltimoTurnoFueDeXEntoncesSigueMas() {
        juego.jugar(1, 1); // X
        assertEquals('+', juego.siguienteJugador());
    }

    @Test
    void siUltimoTurnoFueDeMasEntoncesSigueX() {
        juego.jugar(1, 1); // X
        juego.jugar(1, 2); // +
        assertEquals('X', juego.siguienteJugador());
    }

    // ---------- Requerimiento 3: condicion de victoria ----------

    @Test
    void siNoSeCumpleCondicionDeVictoriaEntoncesNoHayGanador() {
        String resultado = juego.jugar(1, 1);
        assertEquals("No hay ganador", resultado);
    }

    @Test
    void ganaCuandoLlenaUnaLineaHorizontal() {
        juego.jugar(1, 1); // X
        juego.jugar(1, 2); // +
        juego.jugar(2, 1); // X
        juego.jugar(2, 2); // +
        String resultado = juego.jugar(3, 1); // X
        assertEquals("X es el ganador", resultado);
    }

    @Test
    void ganaCuandoLlenaUnaLineaVertical() {
        juego.jugar(2, 1); // X
        juego.jugar(1, 1); // +
        juego.jugar(3, 1); // X
        juego.jugar(1, 2); // +
        juego.jugar(2, 2); // X
        String resultado = juego.jugar(1, 3); // +
        assertEquals("+ es el ganador", resultado);
    }

    @Test
    void ganaCuandoLlenaLaDiagonalPrincipal() {
        juego.jugar(1, 1); // X
        juego.jugar(1, 2); // +
        juego.jugar(2, 2); // X
        juego.jugar(1, 3); // +
        String resultado = juego.jugar(3, 3); // X
        assertEquals("X es el ganador", resultado);
    }

    @Test
    void ganaCuandoLlenaLaDiagonalSecundaria() {
        juego.jugar(1, 1); // X
        juego.jugar(1, 3); // +
        juego.jugar(1, 2); // X
        juego.jugar(2, 2); // +
        juego.jugar(2, 3); // X
        String resultado = juego.jugar(3, 1); // +
        assertEquals("+ es el ganador", resultado);
    }
}
