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
            System.out.println("1 - Escritura de Producto");
            System.out.println("2 - Lectura de Producto");
            System.out.println("3 - Modificación de Producto");
            System.out.println("4 - Listado de Productos");
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
        Producto producto = new Producto();
        ProductoInOut ficheroOut = new ProductoInOut(nombre);

        while (!terminar) {
            System.out.println("[ Alta de Producto ] ");
            producto.lectura();
            ficheroOut.escrituraAdd(producto);
            borrarPantalla();

            System.out.print("¿Dar de alta otro Producto?   (s/n): ");
            String res = LT.llegirLinia();

            if (res.length() == 0 || res.charAt(0) != 's')
                terminar = true;
        }
        
        ficheroOut.cierre();
    }

    public static void lectura(String nombre) {
        boolean terminar = false;
        ProductoInOut ficheroIn = new ProductoInOut(nombre);
        int numProducto;

        Producto producto;

        while (!terminar) {
            borrarPantalla();
            System.out.println("[ Lectura de Producto ] ");
            System.out.print("Introduzca el Producto que desea leer: ");
            numProducto = LT.llegirSencer();
            producto = ficheroIn.lectura(numProducto);

            if (!producto.productoVacio()) {
                System.out.println("Producto " + numProducto + ": " + producto.toString());
            }

            System.out.print("¿Leer otro Producto?   (s/n): ");
            String res = LT.llegirLinia();

            if (res.length() == 0 || res.charAt(0) != 's')
                terminar = true;
        }
        ficheroIn.cierre();
    }

    private static void modificacion(String nombre) {

        boolean terminar = false;
        ProductoInOut ficheroIn = new ProductoInOut(nombre);
        int numProducto;

        while (!terminar) {
            borrarPantalla();
            System.out.println("[ Modificación de Producto ] \n");
            System.out.print("Introduce el Producto que desea modificar: ");
            numProducto = LT.llegirSencer();

            Producto producto = ficheroIn.lectura(numProducto);

            if (!producto.productoVacio()) {
                System.out.println("Producto :" + numProducto + ":");
                System.out.println(producto.toString());
                System.out.println();
                
                System.out.print("¿Modificar código de Producto? (NO -> ENTER): ");
                Integer nuevoCodigo = LT.llegirSencer();
                if (nuevoCodigo != null)
                    producto.setCodigo(nuevoCodigo);
                
                System.out.print("¿Modificar nombre de Producto? (NO -> ENTER): ");
                String nuevoNombre = LT.llegirLinia();
                if (nuevoNombre != null && nuevoNombre.length() > 0)
                    producto.setNombre(nuevoNombre);
                
                System.out.print("¿Modificar NIF de Producto? (NO -> ENTER): ");
                String nuevoNif = LT.llegirLinia();
                if (nuevoNombre != null && nuevoNombre.length() > 0)
                    producto.setNif(nuevoNif);
                
                System.out.print("¿Modificar dirección de Producto? (NO -> ENTER): ");
                String nuevaDireccion = LT.llegirLinia();
                if (nuevoNombre != null && nuevoNombre.length() > 0)
                    producto.setDireccion(nuevaDireccion);
                
                System.out.print("¿Modificar teléfono de Producto? (NO -> ENTER): ");
                Integer nuevoTelefono = LT.llegirSencer();
                if (nuevoTelefono != null)
                    producto.setTelefono(nuevoTelefono);
                
                
                ficheroIn.escritura(numProducto, producto);
                
            }

            System.out.print("¿Desea modificar otro Producto?   (s/n): ");
            String res = LT.llegirLinia();

            if (res.length() == 0 || res.charAt(0) != 's')
                terminar = true;
        }
        ficheroIn.cierre();
    }

    private static void listado(String nombre) {
        borrarPantalla();
        System.out.println("[ Listando Productos ] \n");

        ProductoInOut ficheroIn = new ProductoInOut(nombre);
        Producto producto = ficheroIn.lectura();

        while (!producto.productoVacio()) {
            System.out.println(producto.toString());
            producto = ficheroIn.lectura();
        }

        System.out.println();
        ficheroIn.cierre();
        System.out.print("<< VOLVER MENU PULSAR ENTER >> ");
        LT.llegirCaracter();
    }


}
