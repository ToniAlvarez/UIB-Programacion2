package uib.programacion2.graficos.puzzle_viejo;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Tony on 12/4/17.
 */
public class Tablero extends JFrame {

    private final static int DIM_MATRIZ = 3;
    private final static int DIM_TABLERO = 600;

    private int numMovimientos = 0;

    private Casilla[][] casillas = new Casilla[DIM_MATRIZ][DIM_MATRIZ];

    public Tablero() {

        //Aleatorizar el tablero
        desordenar();

        //Poner la casilla noroeste como la 'vacía'
        obtenerCasilla(0).setBlanca(true);

        add(new Dibujar(casillas, DIM_TABLERO, DIM_TABLERO / DIM_MATRIZ));

        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent ke) {

                boolean casillaEncontrada = false;

                Casilla casillaVacia;

                for (int fila = 0; fila < DIM_MATRIZ; fila++) {
                    for (int columna = 0; columna < DIM_MATRIZ; columna++) {
                        if (casillas[fila][columna].getBlanca()) {

                            switch ((KeyEvent.getKeyText(ke.getKeyCode())).charAt(0)) {
                                case 'W':
                                    if (fila != DIM_MATRIZ - 1) {
                                        //Intercambiar Casillas
                                        casillaVacia = casillas[fila][columna];
                                        casillas[fila][columna] = casillas[fila + 1][columna];
                                        casillas[fila + 1][columna] = casillaVacia;
                                        movimiento();
                                    }
                                    break;
                                case 'D':
                                    if (columna != 0) {
                                        //Intercambiar Casillas
                                        casillaVacia = casillas[fila][columna];
                                        casillas[fila][columna] = casillas[fila][columna - 1];
                                        casillas[fila][columna - 1] = casillaVacia;
                                        movimiento();
                                    }
                                    break;
                                case 'S':
                                    if (fila != 0) {
                                        //Intercambiar Casillas
                                        casillaVacia = casillas[fila][columna];
                                        casillas[fila][columna] = casillas[fila - 1][columna];
                                        casillas[fila - 1][columna] = casillaVacia;
                                        movimiento();
                                    }
                                    break;
                                case 'A':
                                    if (columna != DIM_MATRIZ - 1) {
                                        //Intercambiar Casillas
                                        casillaVacia = casillas[fila][columna];
                                        casillas[fila][columna] = casillas[fila][columna + 1];
                                        casillas[fila][columna + 1] = casillaVacia;
                                        movimiento();
                                    }
                                    break;
                            }

                            casillaEncontrada = true;
                            break;
                        }

                    }

                    if (casillaEncontrada)
                        break;

                }
            }

            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });

        setSize(DIM_TABLERO + 1, DIM_TABLERO + 23);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        repaint();
    }

    /**
     * Después de cada movimiento, repintar el tablero y comprobar si se ha resuelto el puzzle
     */
    public void movimiento() {

        numMovimientos ++;

        System.out.println("\nMovimiento número " + numMovimientos);
        System.out.println("----------------------");
        pintarTablero();

        repaint();

        if (puzzleResuelto()) {
            //Pintar la casilla 'vacia'
            casillas[0][0].setBlanca(false);

            //Avisar al usuario
            System.out.println("Puzzle terminado!!!");
        }
    }

    /**
     * Devuelve:
     * - True si el puzzle está resulto
     * - False en caso contrario
     */

    public boolean puzzleResuelto() {

        for (int fila = 0; fila < DIM_MATRIZ; fila++)
            for (int columna = 0; columna < DIM_MATRIZ; columna++)
                if (casillas[fila][columna].getNumCasilla() != (fila * 3 + columna))
                    return false;

        return true;
    }


    public Casilla obtenerCasilla(int numCasilla) {

        for (int fila = 0; fila < DIM_MATRIZ; fila++)
            for (int columna = 0; columna < DIM_MATRIZ; columna++)
                if (casillas[fila][columna] != null && numCasilla == casillas[fila][columna].getNumCasilla())
                    return casillas[fila][columna];

        return new Casilla(numCasilla);
    }


    public void pintarTablero() {
        System.out.println("  -   0  -  1  -  2");

        for (int fila = 0; fila < DIM_MATRIZ; fila++) {
            System.out.print(fila + " - ");

            for (int columna = 0; columna < DIM_MATRIZ; columna++)
                System.out.print("[ " + casillas[fila][columna].getNumCasilla() + " ] ");

            System.out.println();
        }
    }

    public void desordenar() {
        int numCasillas = DIM_MATRIZ * DIM_MATRIZ - 1;

        int[] arrayOrdenado = new int[numCasillas + 1];
        int[] arrayDesordenado = new int[numCasillas + 1];

        //Rellenar el primer array con enteros ordenados
        for (int i = 0; i < arrayOrdenado.length; i++)
            arrayOrdenado[i] = i;

        //Generador de numeros aleatorios
        Random rand = new Random();
        int randomNum;

        //Variable para activar/desactivar el debug del algoritmo de ordenación
        boolean verbose = true;

        if (verbose) {
            System.out.println("\n===== Contenidos de los arrays antes de pasar por el algoritmo =====");
            System.out.println("Fuente: " + Arrays.toString(arrayOrdenado));
            System.out.println("Resultado: " + Arrays.toString(arrayDesordenado));
        }

        //Algoritmo de ordenacion basado en Fisher–Yates, Durstenfeld's version
        for (int i = 0; i < arrayOrdenado.length; i++) {

            randomNum = rand.nextInt(numCasillas + 1 - i);

            if (verbose) {
                System.out.println("\n===== Iteración nº " + (i + 1) + " del algoritmo de ordenacion =====");
                System.out.println("\nGenerando nº aleatorio. Rango: 0 - " + (numCasillas - i) + ". Resultado: " + randomNum);
            }

            arrayDesordenado[i] = arrayOrdenado[randomNum];
            arrayOrdenado[randomNum] = arrayOrdenado[arrayOrdenado.length - 1 - i];

            if (verbose) {
                System.out.println("Fuente: " + Arrays.toString(arrayOrdenado));
                System.out.println("Resultado: " + Arrays.toString(arrayDesordenado));
            }
        }


        //Ordenar el array bidimensional a partir del resultado del algoritmo de ordenación

        int fila;
        int columna;

        while (numCasillas >= 0) {
            fila = 2 - numCasillas / DIM_MATRIZ;
            columna = 2 - numCasillas % DIM_MATRIZ;

            casillas[fila][columna] = obtenerCasilla(arrayDesordenado[numCasillas]);

            numCasillas--;
        }

        if (verbose) {
            System.out.println("\n===== Contenido del array bidimensional de Casillas desordenado =====");
            pintarTablero();
        }
    }

}