package uib.programacion2.ficheros.aleatorio;


public class Producto {

    //ATRIBUTOS
    static final int DIM_CODIGO = 4;            // int
    static final int DIM_NOMBRE = 40 * 2;       // 40 char
    static final int DIM_NIF = 10 * 2;          // 10 char
    static final int DIM_DIRECCION = 60 * 2;    // 60 char
    static final int DIM_TELEFONO = 4;          // int

    static final int DIM = DIM_CODIGO + DIM_NOMBRE + DIM_NIF + DIM_DIRECCION + DIM_TELEFONO;

    private int codigo = -9999;
    private String nombre;
    private String nif;
    private String direccion;
    private int telefono;

    public Producto() {
    }

    public Producto(int valor) {
        codigo = valor;
    }

    public void lectura() {
        System.out.print("Introduce el código de Producto: ");
        setCodigo(LT.llegirSencer());
        System.out.print("Introduce el nombre de Producto: ");
        setNombre(LT.llegirLinia());
        System.out.print("Introduce el NIF de Producto: ");
        setNif(LT.llegirLinia());
        System.out.print("Introduce la dirección de Producto: ");
        setDireccion(LT.llegirLinia());
        System.out.print("Introduce el teléfono de Producto: ");
        setTelefono(LT.llegirSencer());
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {

        //Si el String es demasiado largo, recortarlo
        //Si es demasiado corto, rellenarlo con espacios
        if (nombre.length() > DIM_NOMBRE / 2) {
            nombre = nombre.substring(0, DIM_NOMBRE / 2);
        } else {
            int espacios = DIM_NOMBRE / 2 - nombre.length();

            for (int i = 0; i < espacios; i++)
                nombre += " ";
        }

        this.nombre = nombre;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        if (nif.length() > DIM_NIF / 2) {
            nif = nif.substring(0, DIM_NIF / 2);
        } else {
            int espacios = DIM_NIF / 2 - nif.length();

            for (int i = 0; i < espacios; i++)
                nif += " ";
        }

        this.nif = nif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion.length() > DIM_DIRECCION / 2) {
            direccion = direccion.substring(0, DIM_DIRECCION / 2);
        } else {
            int espacios = DIM_DIRECCION / 2 - direccion.length();

            for (int i = 0; i < espacios; i++)
                direccion += " ";
        }

        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Producto {" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", nif='" + nif + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono=" + telefono +
                '}';
    }

    public boolean productoVacio() {
        return (codigo == -9999);
    }


}
