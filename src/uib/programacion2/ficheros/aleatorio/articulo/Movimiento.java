package uib.programacion2.ficheros.aleatorio.articulo;

import java.io.Serializable;

public class Movimiento implements Serializable {

    private char[] fecha = new char[8];
    private int codigo = 9999;
    private int unidades;

    public Movimiento() {
    }

    public Movimiento(char[] fecha, int codigo, int unidades) {
        this.fecha = fecha;
        this.codigo = codigo;
        this.unidades = unidades;
    }


    public void lectura() {
        System.out.print("Introduce el código de Articulo: ");
        setCodigo(LT.llegirSencer());
        System.out.print("Introduce descripcion de Articulo: ");
        setFecha(LT.llegirLiniaA());
        System.out.print("Introduce codigo de Movimiento: ");
        setCodigo(LT.llegirSencer());
        System.out.print("Introduce unidades de Movimiento: ");
        setUnidades(LT.llegirSencer());
    }

    public char[] getFecha() {
        return fecha;
    }

    public void setFecha(char[] fecha) {
        this.fecha = fecha;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }
}
