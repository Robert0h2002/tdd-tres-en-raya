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
}
