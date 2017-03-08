package uib.programacion2.ficheros.secuencial.basico;

import java.io.*;

/**
 * Created by Tony on 27/2/17.
 */
public class LibroOut {

    private ObjectOutputStream oos = null;
    private AddObjectOutputStream aoos = null;

    private int codigo = 0;

    public LibroOut(String name) throws Exception {

        File f = new File(name);

        if (f.exists()) {
            aoos = new AddObjectOutputStream(new FileOutputStream(name, true));
            codigo = numeroLibros(name);
        } else {
            oos = new ObjectOutputStream(new FileOutputStream(name));
            codigo = 0;
        }
    }

    public void escritura(Libro libro) throws Exception {

        if (aoos != null)
            aoos.writeObject(libro);
        else
            oos.writeObject(libro);
    }

    public void cierre() throws Exception {
        if (aoos != null)
            aoos.close();
        else
            oos.close();
    }


    private int numeroLibros(String nombreFichero) {

        LibroIn objeto = null;

        int numeroLibros = 0;

        try {
            objeto = new LibroIn(nombreFichero);
            try {
                while (objeto.lectura() != null) {
                    numeroLibros++;
                }
            } catch (EOFException ignored) {
            }

            return numeroLibros;

        } catch (Exception e) {
            System.out.println("ERROR : " + e.toString());
        } finally {
            if (objeto != null) {
                try {
                    objeto.cierre();
                } catch (Exception e) {
                    System.out.println("ERROR : " + e.toString());
                }
            }
        }
        return numeroLibros;
    }

    //Devuelve el código del libro
    public int getCodigo() {
        return codigo;
    }

}
