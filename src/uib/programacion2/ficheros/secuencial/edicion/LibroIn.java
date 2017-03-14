package uib.programacion2.ficheros.secuencial.edicion;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Created by Tony on 27/2/17.
 */
public class LibroIn {

    ObjectInputStream ois = null;

    public LibroIn(FileInputStream fis) throws Exception {
        ois = new ObjectInputStream(fis);
    }

    public LibroIn(String fichero) throws Exception {
        ois = new ObjectInputStream(new FileInputStream(fichero));
    }

    public Libro lectura() throws Exception {
        return (Libro) ois.readObject();
    }

    public void cierre() throws Exception {
        ois.close();
    }


}
