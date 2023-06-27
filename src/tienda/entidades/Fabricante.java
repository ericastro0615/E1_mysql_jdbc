package tienda.entidades;

public class Fabricante {
    //  en este caso no es int porque en algun momento le preguntare si es nulo y solo se puede hacer con integer
    private Integer codigo;
    private String nombre;


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
        this.nombre = nombre;
    }

    public Fabricante(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Fabricante() {
    }

    @Override
    public String toString() {
        return "Fabricante" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'';
    }
}
