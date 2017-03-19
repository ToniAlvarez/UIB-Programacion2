package uib.programacion2.ficheros.aleatorio.producto;

import java.io.IOException;
import java.io.RandomAccessFile;


public class ProductoInOut {
    private RandomAccessFile objetoInOut = null;

    public ProductoInOut(String nombre) {
        try {
            objetoInOut = new RandomAccessFile(nombre, "rw");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Producto lectura() {
        Producto producto = new Producto();
        try {
            if (objetoInOut.getFilePointer() < objetoInOut.length()) {
                try {

                    leerProducto(producto);

                    return producto;
                } catch (IOException error) {
                }
                return producto;
            } else {
                return producto;
            }
        } catch (IOException error) {
        }
        return producto;
    }

    public Producto lectura(int numProducto) {
        Producto producto = new Producto();

        try {
            if ((objetoInOut.length()) > ((long) (numProducto - 1) * Producto.DIM)) {
                try {
                    objetoInOut.seek((long) (numProducto - 1) * Producto.DIM);

                    leerProducto(producto);

                    return producto;
                } catch (IOException error) {
                }
            } else {
                throw new ProductoInexistente("Producto INEXISTENTE");
            }
        } catch (IOException error) {
        } catch (ProductoInexistente error) {
            System.err.println("ATENCIÓN: " + error.getMessage());
        }
        return producto;
    }

    public void escritura(int numProducto, Producto producto) {
        try {
            if ((objetoInOut.length()) > ((long) (numProducto - 1) * Producto.DIM)) {
                try {
                    objetoInOut.seek((long) (numProducto - 1) * Producto.DIM);

                    escribirProducto(producto);

                } catch (IOException error) {
                }
            } else {
                throw new ProductoInexistente("Producto INEXISTENTE");
            }

        } catch (IOException error) {
        } catch (ProductoInexistente error) {
            System.err.println("ATENCIÓN: " + error.getMessage());
        }
    }

    public void escrituraAdd(Producto producto) {
        try {
            objetoInOut.seek(objetoInOut.length());

            escribirProducto(producto);

        } catch (IOException error) {
        }
    }

    private void escribirProducto(Producto producto) throws IOException {
        objetoInOut.writeInt(producto.getCodigo());
        objetoInOut.writeChars(producto.getNombre());
        objetoInOut.writeChars(producto.getNif());
        objetoInOut.writeChars(producto.getDireccion());
        objetoInOut.writeInt(producto.getTelefono());
    }

    private void leerProducto(Producto producto) throws IOException {
        //Leer int de Código
        producto.setCodigo(objetoInOut.readInt());

        String nombre = "";

        for (int i = 0; i < Producto.DIM_NOMBRE / 2; i++)
            nombre += objetoInOut.readChar();

        producto.setNombre(nombre);


        String nif = "";

        for (int i = 0; i < Producto.DIM_NIF / 2; i++)
            nif += objetoInOut.readChar();

        producto.setNif(nif);


        String direccion = "";

        for (int i = 0; i < Producto.DIM_DIRECCION / 2; i++)
            direccion += objetoInOut.readChar();

        producto.setDireccion(direccion);

        //Leer int de Telefono
        producto.setTelefono(objetoInOut.readInt());
    }

    public void cierre() {
        if (objetoInOut != null) {
            try {
                objetoInOut.close();
            } catch (IOException error) {
            }
        }

    }

}
    
