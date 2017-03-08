package uib.programacion2.ficheros.secuencial.basico;

import java.io.EOFException;
import java.io.FileNotFoundException;

public class Main {

    private static final char EXIT = 'q';

    private static char opcion;

    public static void main(String[] args) {

        while (opcion != EXIT)
            mostrarMenu();
    }

    //Limpiar la consola
    private static void limpiarConsola() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    private static void mostrarMenu() {

        System.out.println();
        System.out.println("===== MENU DE LA BIBLIOTECA =====");
        System.out.println("----------------------------------------------");
        System.out.println("1 - Dar de alta un nuevo Libro");
        System.out.println("2 - Consultar un libro a partir de su código");
        System.out.println("3 - Listar los libros de la biblioteca");
        System.out.println("----------------------------------------------");
        System.out.println("q - Salir");
        System.out.println();
        System.out.print("Introduzca la acción que desea ejecutar: ");


        opcion = LT.llegirCaracter();

        ejecutarAccion(opcion);
    }


    private static void ejecutarAccion(char accion) {
        switch (accion) {
            case '1':
                System.out.println("Dando de alta un nuevo Libro");
                altaLibro();
                break;
            case '2':
                System.out.println("Consultando un libro");
                consultarLibro();
                break;
            case '3':
                System.out.println("Listando todos los libros de la biblioteca");
                listarLibros();
                break;
            case EXIT:
                System.out.println("Hasta la próxima!");
                break;
            default:
                System.out.println("Acción no reconocida:" + opcion);
                mostrarMenu();
                opcion = 0;
                break;
        }
    }

    private static void altaLibro() {

        LibroOut libroOut = null;

        try {

            libroOut = new LibroOut("biblioteca.dat");

            Libro nuevoLibro = new Libro();
            nuevoLibro.lectura();

            libroOut.escritura(nuevoLibro);

        } catch (Exception e) {
            System.out.println("Error escribiendo fichero: " + e.toString());
            e.printStackTrace();
        } finally {
            if (libroOut != null) {
                try {
                    libroOut.cierre();
                } catch (Exception e) {
                    System.out.println("Error cerrando fichero: " + e.toString());
                }
            }
        }
    }

    private static void consultarLibro() {

        System.out.println("Introduce el CÓDIGO del libro que desea consultar:");
        int codigoConsulta = LT.llegirSencer();

        System.out.println("Buscando el libro " + codigoConsulta + " ...");

        LibroIn libroIn = null;
        Libro libroActual;

        try {
            libroIn = new LibroIn("biblioteca.dat");

            libroActual = libroIn.lectura();

            while (libroActual != null) {
                if (libroActual.getCodigo() == codigoConsulta) {
                    System.out.println(libroActual.toString());
                    break;
                } else
                    libroActual = libroIn.lectura();
            }

        } catch (FileNotFoundException e) {
            System.out.println("La biblioteca está vacía");
            mostrarMenu();
        } catch (EOFException e) {
            System.out.println("Final del fichero: " + e.toString());
        } catch (Exception e) {
            System.out.println("Error leyendo fichero: " + e.toString());
        } finally {
            if (libroIn != null) {
                try {
                    libroIn.cierre();
                } catch (Exception e) {
                    System.out.println("Error cerrando fichero: " + e.toString());
                }
            }
        }
    }

    private static void listarLibros() {

        LibroIn libroIn = null;
        Libro libroActual;

        try {
            libroIn = new LibroIn("biblioteca.dat");

            libroActual = libroIn.lectura();

            while (libroActual != null) {
                System.out.println(libroActual.toString());
                libroActual = libroIn.lectura();
            }

        } catch (FileNotFoundException e) {
            System.out.println("La biblioteca está vacía");
            mostrarMenu();
        } catch (EOFException e) {
            System.out.println("Final del fichero: " + e.toString());
        } catch (Exception e) {
            System.out.println("Error leyendo fichero: " + e.toString());
        } finally {
            if (libroIn != null) {
                try {
                    libroIn.cierre();
                } catch (Exception e) {
                    System.out.println("Error cerrando fichero: " + e.toString());
                }
            }
        }
    }
}
