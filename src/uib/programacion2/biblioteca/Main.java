package uib.programacion2.biblioteca;

import java.io.IOException;

public class Main {

    private static final char EXIT = 'q';
    private static final char IGNORAR_OSX = 10; //Salto de línea en OS X
    private static final char IGNORAR_WIN = 13; //Salto de línea en Windows
    private static char opcion;

    public static void main(String[] args) {

        while (opcion != EXIT)
            mostrarMenu();
    }

    private static void mostrarMenu() {

        if (opcion != IGNORAR_OSX && opcion != IGNORAR_WIN) {
            //Limpiar la consola
            for (int i = 0; i < 50; ++i) System.out.println();

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
        }

        try {
            //Leer solo el primer caracter introducido
            opcion = (char) System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (opcion != IGNORAR_OSX && opcion != IGNORAR_WIN)
            ejecutarAccion(opcion);
    }


    private static void ejecutarAccion(char accion) {
        switch (accion) {
            case '1':
                System.out.println("Dando de alta un nuevo Libro");
                break;
            case '2':
                System.out.println("Consultando un libro");
                break;
            case '3':
                System.out.println("Listando todos los libros de la biblioteca");
                break;
            case EXIT:
                System.out.println("Hasta la próxima!");
                break;
            default:
                System.out.println("Acción no reconocida:" + opcion);
                opcion = 0;
                break;
        }
    }
}
