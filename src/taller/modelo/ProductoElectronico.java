package taller.modelo;

import java.time.LocalDate;
public class ProductoElectronico extends Producto {
    private String marca;
    private int garantiaMeses;

    public ProductoElectronico(String codigo, String nombre, String categoria, double precioCompra, double precioVenta, int stockActual, int stockMinimo, LocalDate fechaIngreso, String proveedor, String marca, int garantiaMeses) {
        super(codigo, nombre, categoria, precioCompra, precioVenta, stockActual, stockMinimo, fechaIngreso, proveedor);
        this.marca = marca;
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + " | Marca: " + getMarca() + " | Garantia meses: " + getGarantiaMeses();
    }

    public String getMarca() {
        return marca;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }
}