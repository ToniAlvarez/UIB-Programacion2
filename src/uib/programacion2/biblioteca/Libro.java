package uib.programacion2.biblioteca;

/**
 * Created by Toni Alvarez on 4/3/17.
 */
public class Libro {

    private int codigo;
    private String titulo;
    private String autor;
    private String editorial;
    private String anyoPublicacion;

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
     * @param anyoPublicacion
     */
    public Libro(int codigo, String titulo, String autor, String editorial, String anyoPublicacion) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anyoPublicacion = anyoPublicacion;
    }

    /**
     * Permite introducir los datos del Libro por consola
     */
    public void lectura() {

    }

    @Override
    /**
     * Devuelve toda la información del Libro
     */
    public String toString() {
        return "Libro{" +
                "codigo=" + codigo +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", editorial='" + editorial + '\'' +
                ", anyoPublicacion='" + anyoPublicacion + '\'' +
                '}';
    }


    /**
     * Devuelve el código del Libro
     * @return int codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Modifica el codigo del Libro
     * @param codigo
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
