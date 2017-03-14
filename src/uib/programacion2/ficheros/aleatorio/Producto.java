package uib.programacion2.ficheros.aleatorio;


public class Producto {

    //ATRIBUTOS
    public static final int DIM = 5;
    int entero = -9999;
    boolean booleano = false;

    public Producto() {
    }

    public Producto(int valor) {
        entero = valor;
        booleano = true;
    }

    public void lectura() {
        System.out.print("ENTERO: ");
        entero = LT.llegirSencer();
        booleano = true;
    }

    public void changeEstado() {
        booleano = !booleano;
    }

    public int getEntero() {
        return entero;
    }

    public void setEntero(int valor) {
        entero = valor;
    }

    public boolean getBooleano() {
        return booleano;
    }

    public void setBooleano(boolean valor) {
        booleano = valor;
    }

    @Override
    public String toString() {
        String tmp = "0";
        if (booleano) {
            tmp = "1";
        }
        return entero + "-" + tmp + " ";
    }

    public boolean productoVacio() {
        return (entero == -9999);
    }


}
