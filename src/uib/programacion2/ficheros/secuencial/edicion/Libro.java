package uib.programacion2.ficheros.secuencial.edicion;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Toni Alvarez on 4/3/17.
 */
public class Libro implements Serializable {

    private int codigo;
    private String titulo;
    private String autor;
    private String editorial;
    private int anyo;

    /**
     * Constructor vacío, el código del Libro será 0
     */
    public Libro() {
        this.codigo = 0;
    }

    /**
     * Constructor con todos los parámetros del Libro
     *
     * @param codigo
     * @param titulo
     * @param autor
     * @param editorial
     * @param anyo
     */
    public Libro(int codigo, String titulo, String autor, String editorial, int anyo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anyo = anyo;
    }

    /**
     * Permite introducir los datos del Libro por consola
     */
    public void lectura() throws IOException {
        System.out.println("Introduce el CÓDIGO del libro:");
        this.codigo = LT.llegirSencer();
        System.out.println("Introduce el TITULO del libro:");
        this.titulo = LT.llegirLinia();
        System.out.println("Introduce el AUTOR del libro:");
        this.autor = LT.llegirLinia();
        System.out.println("Introduce el EDITORIAL del libro:");
        this.editorial = LT.llegirLinia();
        System.out.println("Introduce el AÑO del libro:");
        this.anyo = LT.llegirSencer();
    }

    /**
     * Devuelve toda la información del Libro
     */
    @Override
    public String toString() {
        return "Libro " + codigo + '\n' +
                " - TITULO = " + titulo + '\n' +
                " - AUTOR = " + autor + '\n' +
                " - EDITORIAL = " + editorial + '\n' +
                " - AÑO = " + anyo + '\n';
    }


    /**
     * Devuelve el código del Libro
     *
     * @return int codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Modifica el codigo del Libro
     *
     * @param codigo
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
