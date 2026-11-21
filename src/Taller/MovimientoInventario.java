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
            boolean datosValidos = codigoProducto != null && !codigoProducto.trim().isEmpty()
                    && tipoMovimiento != null && !tipoMovimiento.trim().isEmpty()
                    && fechaMovimiento != null;
            boolean cantidadesValidas = cantidadIngreso >= 0 && cantidadSalida >= 0;
            return datosValidos && cantidadesValidas;
        }
        public String mostrarMovimiento() {
            return String.format("%s -> Prod: %s | Tipo: %s | +%d / -%d",
                    fechaMovimiento, codigoProducto, tipoMovimiento, cantidadIngreso, cantidadSalida);
        }
        public String getCodigoProducto() {
            return codigoProducto;
        }
        public void setCodigoProducto(String codigoProducto) {
            this.codigoProducto = codigoProducto;
        }
        public String getTipoMovimiento() {
            return tipoMovimiento;
        }
        public void setTipoMovimiento(String tipoMovimiento) {
            this.tipoMovimiento = tipoMovimiento;
        }
        public int getCantidadIngreso() {
            return cantidadIngreso;
        }
        public void setCantidadIngreso(int cantidadIngreso) {
            this.cantidadIngreso = cantidadIngreso;
        }
        public int getCantidadSalida() {
            return cantidadSalida;
        }
        public void setCantidadSalida(int cantidadSalida) {
            this.cantidadSalida = cantidadSalida;
        }
        public LocalDate getFechaMovimiento() {
            return fechaMovimiento;
        }
        public void setFechaMovimiento(LocalDate fechaMovimiento) {
            this.fechaMovimiento = fechaMovimiento;
        }
    }
