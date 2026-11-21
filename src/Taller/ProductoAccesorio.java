package Taller;
import java.time.LocalDate;
public class ProductoAccesorio extends Producto {
    private String tipoAccesorio;
    public ProductoAccesorio(String codigo, String nombre, String categoria, double precioCompra, double precioVenta, int stockActual, int stockMinimo, LocalDate fechaIngreso, String proveedor, String tipoAccesorio) {
        super(codigo, nombre, categoria, precioCompra, precioVenta, stockActual, stockMinimo, fechaIngreso, proveedor);
        this.tipoAccesorio = tipoAccesorio;
    }
    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + " | Tipo accesorio: " + tipoAccesorio;
    }
    public String getTipoAccesorio() {
        return tipoAccesorio;
    }
}