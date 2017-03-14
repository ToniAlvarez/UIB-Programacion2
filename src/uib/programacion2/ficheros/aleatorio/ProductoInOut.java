package uib.programacion2.ficheros.aleatorio;

import java.io.IOException;
import java.io.RandomAccessFile;


public class ProductoInOut {
    RandomAccessFile objetoInOut = null;

    public ProductoInOut(String nombre) {
        try {
            objetoInOut = new RandomAccessFile(nombre, "rw");
        } catch (IOException error) {
            
        }
    }

    public Producto lectura() {
        Producto var = new Producto();
        try {
            if (objetoInOut.getFilePointer() < objetoInOut.length()) {
                try {
                    var.setEntero(objetoInOut.readInt());
                    var.setBooleano(objetoInOut.readBoolean());
                    return var;
                } catch (IOException error) {
                }
                return var;
            } else {
                return var;
            }
        } catch (IOException error) {
            
        }
        return var;
    }


    public Producto lectura(int numProducto) {
        Producto var = new Producto();

        try {
            if ((objetoInOut.length()) > ((long) (numProducto - 1) * Producto.DIM)) {
                try {
                    objetoInOut.seek((long) (numProducto - 1) * Producto.DIM);
                    var.setEntero(objetoInOut.readInt());
                    var.setBooleano(objetoInOut.readBoolean());
                    return var;
                } catch (IOException error) {
                }
            } else {
                throw new ProductoInexistente("Producto INEXISTENTE");
            }
        } catch (IOException error) {
        } catch (ProductoInexistente error) {
            System.err.println("ATENCIÓN: " + error.getMessage());
        }
        return var;
    }

    public void escritura(Producto var) {
        try {
            objetoInOut.writeInt(var.getEntero());
            objetoInOut.writeBoolean(var.getBooleano());
        } catch (IOException error) {
        }
    }

    public void escritura(int numProducto, Producto var) {
        try {
            if ((objetoInOut.length()) > ((long) (numProducto - 1) * Producto.DIM)) {
                try {
                    objetoInOut.seek((long) (numProducto - 1) * Producto.DIM);
                    objetoInOut.writeInt(var.getEntero());
                    objetoInOut.writeBoolean(var.getBooleano());
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

    public void escrituraAdd(Producto var) {
        try {
            objetoInOut.seek(objetoInOut.length());
            objetoInOut.writeInt(var.getEntero());
            objetoInOut.writeBoolean(var.getBooleano());
        } catch (IOException error) {
        }
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
    
