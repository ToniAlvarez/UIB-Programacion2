package uib.programacion2.ficheros.secuencial.basico;


/*
CLASE Palabra
*/

import java.io.Serializable;

public class Palabra implements Serializable {

    private static final int MAXIMO = 20;
    private static final char FINAL_SECUENCIA = '.';
    private static final char ESPACIO = ' ';

    private static char caracter = ' ';

    private char caracteres[] = new char[MAXIMO];
    private int numCaracteres;

    public Palabra() {
        numCaracteres = 0;
    }

    @Override
    public String toString() {
        String pal = "";
        for (int i = 0; i < numCaracteres; i++)
            pal = pal + caracteres[i];
        return pal;
    }

    public void lectura() throws Exception {
        numCaracteres = 0;
        while ((caracter != FINAL_SECUENCIA) && (caracter != ESPACIO)) {
            caracteres[numCaracteres] = caracter;
            numCaracteres++;
            caracter = (char) System.in.read();
        }
    }

    public static boolean hayPalabras() throws Exception {
        buscarPalabra();
        return (caracter != FINAL_SECUENCIA);
    }

    private static void buscarPalabra() throws Exception {
        while (caracter == ESPACIO) {
            caracter = (char) System.in.read();
        }
    }


//   public static Palabra palabraCentinela() {
//       Palabra pal=new Palabra();
//       pal.numCaracteres=-1;
//       return pal;
//   }
//
//   public boolean esPalabraCentinela() {
//       return (numCaracteres==-1);
//   }
//

}


