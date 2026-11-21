package Taller;
import java.time.LocalDate;
public class ProductoElectronico extends Producto {
     private String marca;
     private int garantiaMeses;
        public ProductoElectronico(String codigo, String nombre, String categoria, double precioCompra, double precioVenta, int stockActual, int stockMinimo, LocalDate fechaIngreso, String proveedor, String marca, int garantiaMeses) {
            super(codigo, nombre, categoria, precioCompra, precioVenta, stockActual, stockMinimo, fechaIngreso, proveedor);
            this.marca = marca;
            this.garantiaMeses = garantiaMeses;
        }
        /*Verificale ahi creo nos toca corregir o cambiale nose :v*/
        @Override
        public String mostrarInformacion() {
            return super.mostrarInformacion() + String.format(" | Marca: %s | Garantía: %d meses", marca, garantiaMeses);
        }
        public String getMarca() {
            return marca;
        }
        public void setMarca(String marca) {
            this.marca = marca;
        }
        public int getGarantiaMeses() {
            return garantiaMeses;
        }
        public void setGarantiaMeses(int garantiaMeses) {
            this.garantiaMeses = garantiaMeses;
        }
    }

