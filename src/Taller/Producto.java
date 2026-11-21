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
        if (cantidad <= 0) {
            return false;
        }
        stockActual += cantidad;
        return true;
    }

    public boolean disminuirStock(int cantidad) {
        if (cantidad <= 0 || cantidad > stockActual) {
            return false;
        }
        stockActual -= cantidad;
        return true;
    }

    public double calcularValorInventario() {
        return stockActual * precioCompra;
    }

    public boolean reposicion() {
        return stockActual <= stockMinimo;
    }

    public String mostrarInformacion() {
        return "Codigo: " + codigo
                + " | Nombre: " + nombre
                + " | Categoria: " + categoria
                + " | Compra: " + precioCompra
                + " | Venta: " + precioVenta
                + " | Stock: " + stockActual
                + " | Minimo: " + stockMinimo
                + " | Fecha: " + fechaIngreso
                + " | Proveedor: " + proveedor
                + " | Reponer: " + reposicion();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public int getStockActual() {
        return stockActual;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public String getProveedor() {
        return proveedor;
    }
}