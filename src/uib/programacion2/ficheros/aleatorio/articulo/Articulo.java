package uib.programacion2.ficheros.aleatorio.articulo;


public class Articulo {

    //ATRIBUTOS
    static final int DIM_CODIGO = 4;            // int
    static final int DIM_DESC = 20 * 2;         // 20 char
    static final int DIM_PROV = 30 * 2;         // 30 char
    static final int DIM_EXIS = 4;              // int

    static final int DIM_ARTICULO = DIM_CODIGO + DIM_DESC + DIM_PROV + DIM_EXIS;

    private int codigo = -9999;
    private String descripcion;
    private String proveedor;
    private int existencias;

    public Articulo() {
    }

    public Articulo(int valor) {
        codigo = valor;
    }

    public void lectura() {
        System.out.print("Introduce el código de Articulo: ");
        setCodigo(LT.llegirSencer());
        System.out.print("Introduce descripcion de Articulo: ");
        setDescripcion(LT.llegirLinia());
        System.out.print("Introduce proveedor de Articulo: ");
        setProveedor(LT.llegirLinia());
        System.out.print("Introduce existencias del Articulo: ");
        setExistencias(LT.llegirSencer());
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {

        //Si el String es demasiado largo, recortarlo
        //Si es demasiado corto, rellenarlo con espacios
        if (descripcion.length() > DIM_DESC / 2) {
            descripcion = descripcion.substring(0, DIM_DESC / 2);
        } else {
            int espacios = DIM_DESC / 2 - descripcion.length();

            for (int i = 0; i < espacios; i++)
                descripcion += " ";
        }

        this.descripcion = descripcion;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        if (proveedor.length() > DIM_PROV / 2) {
            proveedor = proveedor.substring(0, DIM_PROV / 2);
        } else {
            int espacios = DIM_PROV / 2 - proveedor.length();

            for (int i = 0; i < espacios; i++)
                proveedor += " ";
        }

        this.proveedor = proveedor;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    @Override
    public String toString() {
        return "Articulo {" +
                "codigo=" + codigo +
                ", descripcion='" + descripcion + '\'' +
                ", proveedor='" + proveedor + '\'' +
                ", existencias=" + existencias +
                '}';
    }

    public boolean articuloVacio() {
        return (codigo == -9999);
    }


}
