package uib.programacion2.arrays.ordenacion;


import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Tony on 26/4/17.
 */
public class Insercion {


    public static void main(String[] args) throws IOException {
        System.out.println("Inserccion");
        int[] lista = {23, 234, 54, 434, 213, 59, 76, 25, 21, 13, 9, 2, 6, 3, 1};

        System.out.println("Inicio");
        System.out.println(Arrays.toString(lista));
        System.out.println();
        ordenar(lista);

    }

    private static void ordenar(int[] lista) {
        int j;
        for (int i = 0; i < lista.length; i++) {

            int aux = lista[i];

            for (j = i; j > 0 && aux > lista[j]; j--) {

                lista[j] = lista[j - 1];

            }

            lista[j] = aux;

            System.out.println(Arrays.toString(lista));
        }
    }
}
