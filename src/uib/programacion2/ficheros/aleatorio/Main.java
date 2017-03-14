package uib.programacion2.ficheros.aleatorio;

public class Main {

    public static void main(String[] argumentos) {
        String nombre = "productos.dat";

        boolean fin = false;

        while (!fin) {
            borrarPantalla();

            System.out.println();
            System.out.println("===== MENU =====");
            System.out.println("----------------------------------------------");
            System.out.println("1 - Escritura de producto");
            System.out.println("2 - Lectura de producto");
            System.out.println("3 - Modificación de producto");
            System.out.println("4 - Listado de productos");
            System.out.println("----------------------------------------------");
            System.out.println("q - Salir");
            System.out.println();
            System.out.print("Introduzca la acción que desea ejecutar: ");
            char opcion = LT.llegirCaracter();

            switch (opcion) {
                case '1':
                    alta(nombre);
                    break;
                case '2':
                    lectura(nombre);
                    break;
                case '3':
                    modificacion(nombre);
                    break;
                case '4':
                    listado(nombre);
                    break;
                default:
                    fin = true;
            }
        }
    }


    private static void borrarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.print("\n");
        }
    }

    private static void alta(String nombre) {
        boolean terminar = false;
        Producto ele = new Producto();
        ProductoInOut ficheroOut = new ProductoInOut(nombre);

        while (!terminar) {
            ele.lectura();
            ficheroOut.escrituraAdd(ele);
            borrarPantalla();
            System.out.print("CONTINUAR EN ALTA   (s/n): ");
            char car = LT.llegirCaracter();
            if (car == 'n') {
                terminar = true;
            }
        }
        ficheroOut.cierre();
    }

    public static void lectura(String nombre) {
        boolean terminar = false;
        ProductoInOut ficheroIn = new ProductoInOut(nombre);
        int numProducto;

        Producto ele;

        while (!terminar) {
            borrarPantalla();
            System.err.println("[ LECTURA DE ProductoS ] ");
            System.out.print("NÚMERO DE Producto A LEER: ");
            numProducto = LT.llegirSencer();
            ele = ficheroIn.lectura(numProducto);

            if (!ele.productoVacio()) {
                System.out.println("Producto " + numProducto + ": " + ele.toString());
            }

            System.out.print("CONTINUAR EN LECTURA   (s/n): ");
            char car = LT.llegirCaracter();
            if (car == 'n') {
                terminar = true;
            }
        }
        ficheroIn.cierre();
    }

    private static void modificacion(String nombre) {

        boolean terminar = false;
        ProductoInOut ficheroIn = new ProductoInOut(nombre);
        int numProducto;

        while (!terminar) {
            borrarPantalla();
            System.err.println("[ MODIFICACIÓN DE Producto ] \n");
            System.out.print("NÚMERO DE Producto A MODIFICAR: ");
            numProducto = LT.llegirSencer();

            Producto ele = ficheroIn.lectura(numProducto);

            if (!ele.productoVacio()) {
                System.out.println(ele.toString());
                System.out.print("MODIFICAR CAMPO ENTERO (NO -> ENTER): ");
                Integer valor = LT.llegirSencer();
                if (valor != null) {
                    ele.setEntero(valor);
                }
                System.out.print("MODIFICAR CAMPO BOOLEANO (NO -> ENTER): ");
                valor = LT.llegirSencer();
                if (valor != null) {
                    ele.changeEstado();
                }
                ficheroIn.escritura(numProducto, ele);
            }

            System.out.print("CONTINUAR EN MODIFICACION   (s/n): ");
            char car = LT.llegirCaracter();

            if (car == 'n') {
                terminar = true;
            }
        }
        ficheroIn.cierre();
    }

    private static void listado(String nombre) {
        borrarPantalla();
        System.out.println("[ LISTADO DE ProductoS ] \n");
        ProductoInOut ficheroIn = new ProductoInOut(nombre);
        Producto ele = ficheroIn.lectura();
        while (!ele.productoVacio()) {
            System.out.println(ele.toString());
            ele = ficheroIn.lectura();
        }
        System.out.println();
        ficheroIn.cierre();
        System.out.print("<< VOLVER MENU PULSAR ENTER >> ");
        Character car = LT.llegirCaracter();
    }


}
