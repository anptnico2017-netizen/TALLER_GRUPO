package Taller;
import java.time.LocalDate;
import java.util.ArrayList;
public class Inventario {
    private ArrayList<Producto> productos;
    private ArrayList<MovimientoInventario> movimientos;
    private double presupuestoDisponible;
    private double costoTotalInventario;
    private double capacidadMaxima;
    private double espacioOcupado;

    public Inventario(double presupuestoDisponible, double capacidadMaxima) {
        this.productos = new ArrayList<>();
        this.movimientos = new ArrayList<>();
        this.presupuestoDisponible = presupuestoDisponible;
        this.capacidadMaxima = capacidadMaxima;
        this.espacioOcupado = 0;
        this.costoTotalInventario = 0;
    }

    public boolean registrarProducto(Producto producto) {
        if (producto == null) {
            return false;
        }
        if (producto.getCodigo() == null || producto.getCodigo().isEmpty()
                || producto.getNombre() == null || producto.getNombre().isEmpty()
                || producto.getCategoria() == null || producto.getCategoria().isEmpty()
                || producto.getProveedor() == null || producto.getProveedor().isEmpty()
                || producto.getFechaIngreso() == null) {
            return false;
        }
        if (producto.getPrecioCompra() <= 0 || producto.getPrecioVenta() <= 0
                || producto.getStockActual() < 0 || producto.getStockMinimo() < 0) {
            return false;
        }
        if (buscarProducto(producto.getCodigo()) != null) {
            return false;
        }

        double costoInicial = producto.getStockActual() * producto.getPrecioCompra();
        double espacioNecesario = producto.getStockActual();

        if (!verificarPresupuesto(costoInicial) || !verificarEspacio(espacioNecesario)) {
            return false;
        }

        productos.add(producto);
        presupuestoDisponible -= costoInicial;
        espacioOcupado += espacioNecesario;
        costoTotalInventario = calcularValorTotalInventario();

        if (producto.getStockActual() > 0) {
            MovimientoInventario mov = new MovimientoInventario(
                    producto.getCodigo(),
                    "Registro",
                    producto.getStockActual(),
                    0,
                    LocalDate.now()
            );
            if (mov.registrarMovimiento()) {
                movimientos.add(mov);
            }
        }
        return true;
    }

    public Producto buscarProducto(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    public boolean eliminarProducto(String codigo) {
        Producto p = buscarProducto(codigo);
        if (p == null) {
            return false;
        }
        espacioOcupado -= p.getStockActual();
        if (espacioOcupado < 0) {
            espacioOcupado = 0;
        }
        productos.remove(p);
        costoTotalInventario = calcularValorTotalInventario();
        return true;
    }

    public boolean registrarEntrada(String codigo, int cantidad) {
        Producto p = buscarProducto(codigo);
        if (p == null || cantidad <= 0 || !verificarEspacio(cantidad)) {
            return false;
        }
        if (!p.aumentarStock(cantidad)) {
            return false;
        }
        espacioOcupado += cantidad;
        costoTotalInventario = calcularValorTotalInventario();

        MovimientoInventario mov = new MovimientoInventario(
                p.getCodigo(),
                "Entrada",
                cantidad,
                0,
                LocalDate.now()
        );
        if (mov.registrarMovimiento()) {
            movimientos.add(mov);
        }
        return true;
    }

    public boolean registrarSalida(String codigo, int cantidad) {
        Producto p = buscarProducto(codigo);
        if (p == null || cantidad <= 0) {
            return false;
        }
        if (!p.disminuirStock(cantidad)) {
            return false;
        }
        espacioOcupado -= cantidad;
        if (espacioOcupado < 0) {
            espacioOcupado = 0;
        }
        costoTotalInventario = calcularValorTotalInventario();

        MovimientoInventario mov = new MovimientoInventario(
                p.getCodigo(),
                "Salida",
                0,
                cantidad,
                LocalDate.now()
        );
        if (mov.registrarMovimiento()) {
            movimientos.add(mov);
        }
        return true;
    }

    public boolean realizarCompra(String codigo, int cantidad) {
        Producto p = buscarProducto(codigo);
        if (p == null || cantidad <= 0) {
            return false;
        }
        double costo = cantidad * p.getPrecioCompra();
        if (!verificarPresupuesto(costo) || !verificarEspacio(cantidad)) {
            return false;
        }
        if (!p.aumentarStock(cantidad)) {
            return false;
        }
        presupuestoDisponible -= costo;
        espacioOcupado += cantidad;
        costoTotalInventario = calcularValorTotalInventario();

        MovimientoInventario mov = new MovimientoInventario(
                p.getCodigo(),
                "Compra",
                cantidad,
                0,
                LocalDate.now()
        );
        if (mov.registrarMovimiento()) {
            movimientos.add(mov);
        }
        return true;
    }

    public void mostrarProductos() {
        for (Producto p : productos) {
            System.out.println(p.mostrarInformacion());
        }
    }

    public void mostrarMovimientos() {
        for (MovimientoInventario m : movimientos) {
            System.out.println(m.mostrarMovimiento());
        }
    }

    public void mostrarProductosBajoStock() {
        for (Producto p : productos) {
            if (p.reposicion()) {
                System.out.println(p.mostrarInformacion());
            }
        }
    }

    public double calcularValorTotalInventario() {
        double total = 0;
        for (Producto p : productos) {
            total += p.calcularValorInventario();
        }
        costoTotalInventario = total;
        return costoTotalInventario;
    }

    public boolean verificarPresupuesto(double costo) {
        return costo >= 0 && costo <= presupuestoDisponible;
    }

    public boolean verificarEspacio(double espacio) {
        return espacio >= 0 && (espacioOcupado + espacio) <= capacidadMaxima;
    }

    public double getPresupuestoDisponible() {
        return presupuestoDisponible;
    }

    public double getCostoTotalInventario() {
        return costoTotalInventario;
    }

    public double getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public double getEspacioOcupado() {
        return espacioOcupado;
    }
}