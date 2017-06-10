package uib.programacion2.ficheros.practica;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Tony on 5/4/17.
 */
public class JugadorTextOut {

    FileWriter fw;

    public JugadorTextOut(String fichero) {
        try {
            fw = new FileWriter(fichero);
        } catch (IOException e) {
            System.err.println("Error al abrir el fichero de Jugadas destacadas:");
            e.printStackTrace();
        }
    }

    public void escribir(String texto) {
        try {
            fw.write(texto + System.getProperty("line.separator"));
        } catch (IOException e) {
            System.err.println("Error al escribir en el fichero de Jugadas destacadas:");
            e.printStackTrace();
        }
    }

    public void cierre() {
        try {
            fw.close();
        } catch (IOException e) {
            System.err.println("Error al cerrar el fichero de Jugadas destacadas:");
            e.printStackTrace();
        }
    }
}