package uib.programacion2.arrays.ordenacion;


import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Tony on 26/4/17.
 */
public class Seleccion {


    public static void main(String[] args) throws IOException {

        int[] lista = {23, 234, 54, 434, 213, 59, 76, 25, 21, 13, 9, 2, 6, 3, 1};

        System.out.println("Inicio");
        System.out.println(Arrays.toString(lista));
        System.out.println();
        ordenar(lista);

    }

    private static void ordenar(int[] lista) {
        for (int i = 0; i < lista.length; i++) {
            int aux = 0;
            int menor = i;

            for (int j = i; j < lista.length; j++) {

                if (lista[j] < lista[menor])
                    menor = j;
            }

            aux = lista[menor];
            lista[menor] = lista[i];
            lista[i] = aux;


            System.out.println(Arrays.toString(lista));
        }
    }
}
