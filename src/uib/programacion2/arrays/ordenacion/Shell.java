package uib.programacion2.arrays.ordenacion;


import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Tony on 26/4/17.
 */
public class Shell {


    public static void main(String[] args) throws IOException {

        int[] lista = {23, 234, 54, 434, 213, 54, 76, 23, 21, 13, 9, 2, 6, 3, 1};

        System.out.println("Inicio");
        System.out.println(Arrays.toString(lista));
        System.out.println();
        ordenar(lista);

    }

    //Falta bool para saber si ya esta ordenado
    private static void ordenar(int[] lista) {
        for (int i = 0; i < lista.length - 1; i++) {
            for (int j = 1; j < lista.length - i; j++) {
                int antes = 0;
                int aux = 0;

                if (lista[j] < lista[j - 1]) {
                    aux = lista[j - 1];
                    lista[j - 1] = lista[j];
                    lista[j] = aux;
                }

                System.out.println("J " + j + "\t-\t" + Arrays.toString(lista));
            }

            System.out.println("\nI " + i + " - " + Arrays.toString(lista) + "\n");
        }
    }
}
