package com.ucb.tdd;

public class TresEnRaya {

    private char[][] tablero = new char[3][3];

    public void jugar(int x, int y) {
        if (x < 1 || x > 3) {
            throw new RuntimeException("X esta fuera del tablero");
        }
        if (y < 1 || y > 3) {
            throw new RuntimeException("Y esta fuera del tablero");
        }
        if (tablero[x - 1][y - 1] != '\0') {
            throw new RuntimeException("El lugar ya esta ocupado");
        }
        tablero[x - 1][y - 1] = 'X';
    }
}
