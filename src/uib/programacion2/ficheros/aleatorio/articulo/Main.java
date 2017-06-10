package uib.programacion2.ficheros.aleatorio.articulo;

public class Main {

    public static void main(String[] argumentos) {
        String nombre = "articulos.dat";

        boolean fin = false;

        while (!fin) {
            borrarPantalla();

            System.out.println();
            System.out.println("===== MENU =====");
            System.out.println("----------------------------------------------");
            System.out.println("1 - Generar 10 ficheros de Movimientos");
            System.out.println("1 - Fusionar ficheros de Movimientos");
            System.out.println("3 - Actualizar fichero de Articulo");
            System.out.println("4 - Listar Articulos con bajas existencias");
            System.out.println("5 - Listar todos los Articulos");
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
            System.out.println();
        }
    }

    private static void alta(String nombre) {
        boolean terminar = false;
        Articulo articulo = new Articulo();
        ArticuloInOut ficheroOut = new ArticuloInOut(nombre);

        while (!terminar) {
            System.out.println("[ Alta de Articulo ] ");
            articulo.lectura();
            ficheroOut.escrituraAdd(articulo);
            borrarPantalla();

            System.out.print("¿Dar de alta otro Articulo?   (s/n): ");
            String res = LT.llegirLinia();

            if (res.length() == 0 || res.charAt(0) != 's')
                terminar = true;
        }

        ficheroOut.cierre();
    }

    public static void lectura(String nombre) {
        boolean terminar = false;
        ArticuloInOut ficheroIn = new ArticuloInOut(nombre);
        int numArticulo;

        Articulo articulo;

        while (!terminar) {
            borrarPantalla();
            System.out.println("[ Lectura de Articulo ] ");
            System.out.print("Introduzca el Articulo que desea leer: ");
            numArticulo = LT.llegirSencer();
            articulo = ficheroIn.lectura(numArticulo);

            if (!articulo.articuloVacio()) {
                System.out.println("Articulo " + numArticulo + ": " + articulo.toString());
            }

            System.out.print("¿Leer otro Articulo?   (s/n): ");
            String res = LT.llegirLinia();

            if (res.length() == 0 || res.charAt(0) != 's')
                terminar = true;
        }
        ficheroIn.cierre();
    }

    private static void modificacion(String nombre) {

        boolean terminar = false;
        ArticuloInOut ficheroIn = new ArticuloInOut(nombre);
        int numArticulo;

        while (!terminar) {
            borrarPantalla();
            System.out.println("[ Modificación de Articulo ] \n");
            System.out.print("Introduce el Articulo que desea modificar: ");
            numArticulo = LT.llegirSencer();

            Articulo articulo = ficheroIn.lectura(numArticulo);

            if (!articulo.articuloVacio()) {
                System.out.println("Articulo :" + numArticulo + ":");
                System.out.println(articulo.toString());
                System.out.println();

                System.out.print("¿Modificar código de Articulo? (NO -> ENTER): ");
                Integer nuevoCodigo = LT.llegirSencer();
                if (nuevoCodigo != null)
                    articulo.setCodigo(nuevoCodigo);

                System.out.print("¿Modificar descripcion de Articulo? (NO -> ENTER): ");
                String nuevaDesc = LT.llegirLinia();
                if (nuevaDesc != null && nuevaDesc.length() > 0)
                    articulo.setDescripcion(nuevaDesc);

                System.out.print("¿Modificar proveedor de Articulo? (NO -> ENTER): ");
                String nuevoProv = LT.llegirLinia();
                if (nuevaDesc != null && nuevaDesc.length() > 0)
                    articulo.setProveedor(nuevoProv);

                System.out.print("¿Modificar existencias de Articulo? (NO -> ENTER): ");
                Integer nuevasExist = LT.llegirSencer();
                if (nuevasExist != null)
                    articulo.setExistencias(nuevasExist);


                ficheroIn.escritura(numArticulo, articulo);

            }

            System.out.print("¿Desea modificar otro Articulo?   (s/n): ");
            String res = LT.llegirLinia();

            if (res.length() == 0 || res.charAt(0) != 's')
                terminar = true;
        }
        ficheroIn.cierre();
    }

    private static void listado(String nombre) {
        borrarPantalla();
        System.out.println("[ Listando Articulos ] \n");

        ArticuloInOut ficheroIn = new ArticuloInOut(nombre);
        Articulo articulo = ficheroIn.lectura();

        while (!articulo.articuloVacio()) {
            System.out.println(articulo.toString());
            articulo = ficheroIn.lectura();
        }

        System.out.println();
        ficheroIn.cierre();
        System.out.print("<< VOLVER MENU PULSAR ENTER >> ");
        LT.llegirCaracter();
    }


}
