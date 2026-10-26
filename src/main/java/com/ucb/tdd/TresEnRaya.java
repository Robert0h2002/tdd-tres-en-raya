package com.ucb.tdd;

public class TresEnRaya {

    private char[][] tablero = new char[3][3];
    private char ultimoJugador = '+';

    public String jugar(int x, int y) {
        if (x < 1 || x > 3) {
            throw new RuntimeException("X esta fuera del tablero");
        }
        if (y < 1 || y > 3) {
            throw new RuntimeException("Y esta fuera del tablero");
        }
        if (tablero[x - 1][y - 1] != '\0') {
            throw new RuntimeException("El lugar ya esta ocupado");
        }
        ultimoJugador = siguienteJugador();
        tablero[x - 1][y - 1] = ultimoJugador;

        for (int i = 0; i < 3; i++) {
            // linea horizontal (misma y)
            if (tablero[0][i] == ultimoJugador && tablero[1][i] == ultimoJugador && tablero[2][i] == ultimoJugador) {
                return ultimoJugador + " es el ganador";
            }
            // linea vertical (misma x)
            if (tablero[i][0] == ultimoJugador && tablero[i][1] == ultimoJugador && tablero[i][2] == ultimoJugador) {
                return ultimoJugador + " es el ganador";
            }
        }
        if (tablero[0][0] == ultimoJugador && tablero[1][1] == ultimoJugador && tablero[2][2] == ultimoJugador) {
            return ultimoJugador + " es el ganador";
        }
        if (tablero[0][2] == ultimoJugador && tablero[1][1] == ultimoJugador && tablero[2][0] == ultimoJugador) {
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
}
