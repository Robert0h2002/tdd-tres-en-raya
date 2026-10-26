package com.ucb.tdd;

/**
 * Kata Tres en Raya resuelta con TDD.
 * Las coordenadas van de 1 a 3: x es el eje horizontal y y el eje vertical.
 */
public class TresEnRaya {

    private static final int TAMANIO = 3;
    private static final char VACIO = '\0';

    private final char[][] tablero = new char[TAMANIO][TAMANIO];
    private char ultimoJugador = '+';

    public String jugar(int x, int y) {
        verificarEje(x, "X");
        verificarEje(y, "Y");
        colocarPieza(x, y);

        if (hayGanador()) {
            return ultimoJugador + " es el ganador";
        }
        return "No hay ganador";
    }

    public char siguienteJugador() {
        if (ultimoJugador == 'X') {
            return '+';
        }
        return 'X';
    }

    private void verificarEje(int valor, String eje) {
        if (valor < 1 || valor > TAMANIO) {
            throw new RuntimeException(eje + " esta fuera del tablero");
        }
    }

    private void colocarPieza(int x, int y) {
        if (tablero[x - 1][y - 1] != VACIO) {
            throw new RuntimeException("El lugar ya esta ocupado");
        }
        ultimoJugador = siguienteJugador();
        tablero[x - 1][y - 1] = ultimoJugador;
    }

    private boolean hayGanador() {
        for (int i = 0; i < TAMANIO; i++) {
            if (esLinea(tablero[0][i], tablero[1][i], tablero[2][i])      // horizontal
                    || esLinea(tablero[i][0], tablero[i][1], tablero[i][2])) { // vertical
                return true;
            }
        }
        return esLinea(tablero[0][0], tablero[1][1], tablero[2][2])       // diagonal
                || esLinea(tablero[0][2], tablero[1][1], tablero[2][0]);  // diagonal secundaria
    }

    private boolean esLinea(char a, char b, char c) {
        return a == ultimoJugador && b == ultimoJugador && c == ultimoJugador;
    }
}
