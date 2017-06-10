package uib.programacion2.ficheros.aleatorio.articulo;

import java.io.IOException;
import java.io.RandomAccessFile;


public class ArticuloInOut {
    private RandomAccessFile objetoInOut = null;

    public ArticuloInOut(String nombre) {
        try {
            objetoInOut = new RandomAccessFile(nombre, "rw");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Articulo lectura() {
        Articulo articulo = new Articulo();
        try {
            if (objetoInOut.getFilePointer() < objetoInOut.length()) {
                try {

                    leerProducto(articulo);

                    return articulo;
                } catch (IOException error) {
                }
                return articulo;
            } else {
                return articulo;
            }
        } catch (IOException error) {
        }
        return articulo;
    }

    public Articulo lectura(int numProducto) {
        Articulo articulo = new Articulo();

        try {
            if ((objetoInOut.length()) > ((long) (numProducto - 1) * Articulo.DIM_ARTICULO)) {
                try {
                    objetoInOut.seek((long) (numProducto - 1) * Articulo.DIM_ARTICULO);

                    leerProducto(articulo);

                    return articulo;
                } catch (IOException error) {
                }
            } else {
                throw new ArticuloInexistente("Articulo INEXISTENTE");
            }
        } catch (IOException error) {
        } catch (ArticuloInexistente error) {
            System.err.println("ATENCIÓN: " + error.getMessage());
        }
        return articulo;
    }

    public void escritura(int numProducto, Articulo articulo) {
        try {
            if ((objetoInOut.length()) > ((long) (numProducto - 1) * Articulo.DIM_ARTICULO)) {
                try {
                    objetoInOut.seek((long) (numProducto - 1) * Articulo.DIM_ARTICULO);

                    escribirProducto(articulo);

                } catch (IOException error) {
                }
            } else {
                throw new ArticuloInexistente("Articulo INEXISTENTE");
            }

        } catch (IOException error) {
        } catch (ArticuloInexistente error) {
            System.err.println("ATENCIÓN: " + error.getMessage());
        }
    }

    public void escrituraAdd(Articulo articulo) {
        try {
            objetoInOut.seek(objetoInOut.length());

            escribirProducto(articulo);

        } catch (IOException error) {
        }
    }

    private void escribirProducto(Articulo articulo) throws IOException {
        objetoInOut.writeInt(articulo.getCodigo());
        objetoInOut.writeChars(articulo.getDescripcion());
        objetoInOut.writeChars(articulo.getProveedor());
        objetoInOut.writeInt(articulo.getExistencias());
    }

    private void leerProducto(Articulo articulo) throws IOException {
        //Leer int de Código
        articulo.setCodigo(objetoInOut.readInt());

        String descripcion = "";

        for (int i = 0; i < Articulo.DIM_DESC / 2; i++)
            descripcion += objetoInOut.readChar();

        articulo.setDescripcion(descripcion);


        String proveedor = "";

        for (int i = 0; i < Articulo.DIM_PROV / 2; i++)
            proveedor += objetoInOut.readChar();

        articulo.setProveedor(proveedor);

        //Leer int de existencias
        articulo.setExistencias(objetoInOut.readInt());
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
    
