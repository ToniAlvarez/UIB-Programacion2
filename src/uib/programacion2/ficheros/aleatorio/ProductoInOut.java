package uib.programacion2.ficheros.aleatorio;

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
                    objetoInOut.writeInt(producto.getCodigo());
                    objetoInOut.writeChars(producto.getNombre());
                    objetoInOut.writeChars(producto.getNif());
                    objetoInOut.writeChars(producto.getDireccion());
                    objetoInOut.writeInt(producto.getTelefono());


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

            objetoInOut.writeInt(producto.getCodigo());
            objetoInOut.writeChars(producto.getNombre());
            objetoInOut.writeChars(producto.getNif());
            objetoInOut.writeChars(producto.getDireccion());
            objetoInOut.writeInt(producto.getTelefono());
        } catch (IOException error) {
        }
    }

    private void leerProducto(Producto producto) throws IOException {
        //Leer int de Código
        producto.setCodigo(objetoInOut.readInt());

        //Leer chars del Nombre
        byte[] bytesNombre = new byte[Producto.DIM_NOMBRE];
        objetoInOut.read(bytesNombre);
        producto.setNombre(new String(bytesNombre));

        //Leer chars del NIF
        byte[] bytesNif = new byte[Producto.DIM_NIF];
        objetoInOut.read(bytesNif);
        producto.setNif(new String(bytesNif));

        //Leer chars de la Direccion
        byte[] bytesDireccion = new byte[Producto.DIM_DIRECCION];
        objetoInOut.read(bytesDireccion);
        producto.setDireccion(new String(bytesDireccion));

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
    
