/*
FUNCIONALIDAD: entrada de datos por teclado.

 */
package uib.programacion2.ficheros.secuencial.edicion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LT {

    private static char[] secuencia;
    private static int indice = 0;

    private static char[] llegirln() {

        String res = "";
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(System.in, "ISO-8859-1"));
            res = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res.toCharArray();
    }

    public static char llegirSecuenciaCaracteres() {
        int elemento = 0;

        if (indice == 0) {
            secuencia = llegirln();
        }

        elemento = indice;
        indice = (secuencia[indice] == '.') ? 0 : (indice + 1);

        return (secuencia[elemento]);

    }

    public static Integer llegirSencer() {

        Integer res = 0;

        try {
            char[] pal = llegirln();
            res = Integer.parseInt(new String(pal));
        } catch (NumberFormatException e) {
            res = null;
        }

        return res;
    }

    public static Double llegirReal() {

        Double res = 0.0;

        try {
            char[] pal = llegirln();
            res = Double.parseDouble(new String(pal));
        } catch (NumberFormatException e) {
            res = null;
        }

        return res;
    }

    public static String llegirLinia() {

        String res;
        char[] pal = llegirln();
        res = new String(pal);

        return res;
    }

    public static Character llegirCaracter() {

        Character res;
        char[] pal = llegirln();
        String s = new String(pal);

        if (s.length() == 0) {
            res = null;
        } else {
            res = s.charAt(0);
        }

        return res;
    }

    public static char[] llegirLiniaA() {
        char res[];
        String pal = llegirLinia();
        res = pal.toCharArray();
        return res;
    }
}