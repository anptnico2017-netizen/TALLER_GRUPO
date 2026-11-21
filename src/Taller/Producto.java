package Taller;
import java.time.LocalDate;
public class Producto {
        private String codigo;
        private String nombre;
        private String categoria;
        private double precioCompra;
        private double precioVenta;
        private int stockActual;
        private int stockMinimo;
        private LocalDate fechaIngreso;
        private String proveedor;
        public Producto(String codigo, String nombre, String categoria, double precioCompra, double precioVenta, int stockActual, int stockMinimo, LocalDate fechaIngreso, String proveedor) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.categoria = categoria;
            this.precioCompra = precioCompra;
            this.precioVenta = precioVenta;
            this.stockActual = stockActual;
            this.stockMinimo = stockMinimo;
            this.fechaIngreso = fechaIngreso;
            this.proveedor = proveedor;
        }
        public boolean aumentarStock(int cantidad) {
            boolean valido = cantidad > 0;
            if (valido) {
                stockActual = stockActual + cantidad;
            }
            return valido;
        }
        public boolean disminuirStock(int cantidad) {
            boolean valido = cantidad > 0 && cantidad <= stockActual;
            if (valido) {
                stockActual = stockActual - cantidad;
            }
            return valido;
        }
        public double calcularValorInventario() {
            return precioCompra * stockActual;
        }
        public boolean reposicion() {
            return stockActual <= stockMinimo;
        }
        public String mostrarInformacion() {
            String estadoReposicion = reposicion() ? "Requiere una reposicion" : "Stock adecado";
            return String.format(
                    "[%s] %s (%s) | Compra: %.2f | Venta: %.2f | Stock: %d (Min: %d) | Ingreso: %s | Prov: %s | %s",
                    codigo, nombre, categoria, precioCompra, precioVenta, stockActual, stockMinimo, fechaIngreso, proveedor, estadoReposicion
            );
        }
        public String getCodigo() {
            return codigo;
        }
        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }
        public String getNombre() {
            return nombre;
        }
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        public String getCategoria() {
            return categoria;
        }
        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }
        public double getPrecioCompra() {
            return precioCompra;
        }
        public void setPrecioCompra(double precioCompra) {
            this.precioCompra = precioCompra;
        }
        public double getPrecioVenta() {
            return precioVenta;
        }
        public void setPrecioVenta(double precioVenta) {
            this.precioVenta = precioVenta;
        }
        public int getStockActual() {
            return stockActual;
        }
        public void setStockActual(int stockActual) {
            this.stockActual = stockActual;
        }
        public int getStockMinimo() {
            return stockMinimo;
        }
        public void setStockMinimo(int stockMinimo) {
            this.stockMinimo = stockMinimo;
        }
        public LocalDate getFechaIngreso() {
            return fechaIngreso;
        }
        public void setFechaIngreso(LocalDate fechaIngreso) {
            this.fechaIngreso = fechaIngreso;
        }
        public String getProveedor() {
            return proveedor;
        }
        public void setProveedor(String proveedor) {
            this.proveedor = proveedor;
        }
    }