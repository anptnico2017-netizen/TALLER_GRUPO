package Taller;
import java.time.LocalDate;
public class MovimientoInventario {
    private String codigoProducto;
    private String tipoMovimiento;
    private int cantidadIngreso;
    private int cantidadSalida;
    private LocalDate fechaMovimiento;
    public MovimientoInventario(String codigoProducto, String tipoMovimiento, int cantidadIngreso, int cantidadSalida, LocalDate fechaMovimiento) {
        this.codigoProducto = codigoProducto;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidadIngreso = cantidadIngreso;
        this.cantidadSalida = cantidadSalida;
        this.fechaMovimiento = fechaMovimiento;
    }

    public boolean registrarMovimiento() {
        return codigoProducto != null && !codigoProducto.isEmpty()
                && tipoMovimiento != null && !tipoMovimiento.isEmpty()
                && fechaMovimiento != null
                && cantidadIngreso >= 0 && cantidadSalida >= 0;
    }

    public String mostrarMovimiento() {
        return "Fecha: " + fechaMovimiento
                + " | Codigo: " + codigoProducto
                + " | Tipo: " + tipoMovimiento
                + " | Ingreso: " + cantidadIngreso
                + " | Salida: " + cantidadSalida;
    }
}
