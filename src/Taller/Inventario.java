package Taller;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Inventario {
        private List<Producto> productos;
        private List<MovimientoInventario> movimientos;
        private double presupuestoDisponible;
        private double costoTotalInventario;
        private double capacidadMaxima;
        private double espacioOcupado;
        public Inventario(double presupuestoDisponible, double capacidadMaxima) {
            this.productos = new ArrayList<>();
            this.movimientos = new ArrayList<>();
            this.presupuestoDisponible = presupuestoDisponible > 0 ? presupuestoDisponible : 0;
            this.capacidadMaxima = capacidadMaxima > 0 ? capacidadMaxima : 0;
            this.espacioOcupado = 0;
            this.costoTotalInventario = 0;
        }
        private void anexarMovimiento(String codigo, String tipo, int ingreso, int salida) {
            MovimientoInventario registro = new MovimientoInventario(codigo, tipo, ingreso, salida, LocalDate.now());
            if (registro.registrarMovimiento()) {
                movimientos.add(registro);
            }
        }
        private void actualizarMetricas() {
            this.costoTotalInventario = calcularValorTotalInventario();
        }
        public boolean registrarProducto(Producto producto) {
            if (producto == null || buscarProducto(producto.getCodigo()) != null) {
                return false;
            }
            boolean textosOk = producto.getCodigo() != null && !producto.getCodigo().isBlank()
                    && producto.getNombre() != null && !producto.getNombre().isBlank()
                    && producto.getCategoria() != null && !producto.getCategoria().isBlank()
                    && producto.getProveedor() != null && !producto.getProveedor().isBlank()
                    && producto.getFechaIngreso() != null;
            boolean numerosOk = producto.getPrecioCompra() > 0
                    && producto.getPrecioVenta() > 0
                    && producto.getStockActual() >= 0
                    && producto.getStockMinimo() >= 0;
            if (!textosOk || !numerosOk) {
                return false;
            }
            double costoInicial = producto.getStockActual() * producto.getPrecioCompra();
            double espacioRequerido = producto.getStockActual();

            if (!verificarPresupuesto(costoInicial) || !verificarEspacio(espacioRequerido)) {
                return false;
            }
            productos.add(producto);
            presupuestoDisponible -= costoInicial;
            espacioOcupado += espacioRequerido;
            actualizarMetricas();

            if (producto.getStockActual() > 0) {
                anexarMovimiento(producto.getCodigo(), "Alta", producto.getStockActual(), 0);
            }
            return true;
        }

        public Producto buscarProducto(String codigo) {
            if (codigo == null || codigo.isBlank()) {
                return null;
            }
            int indice = 0;
            while (indice < productos.size()) {
                Producto actual = productos.get(indice);
                if (actual.getCodigo().equalsIgnoreCase(codigo)) {
                    return actual;
                }
                indice++;
            }
            return null;
        }

        public boolean eliminarProducto(String codigo) {
            Producto objetivo = buscarProducto(codigo);
            if (objetivo == null) {
                return false;
            }
            espacioOcupado -= objetivo.getStockActual();
            if (espacioOcupado < 0) {
                espacioOcupado = 0;
            }
            boolean eliminado = productos.remove(objetivo);
            actualizarMetricas();
            return eliminado;
        }

        public boolean registrarEntrada(String codigo, int cantidad) {
            Producto prod = buscarProducto(codigo);
            if (prod == null || cantidad <= 0 || !verificarEspacio(cantidad)) {
                return false;
            }
            if (!prod.aumentarStock(cantidad)) {
                return false;
            }
            espacioOcupado += cantidad;
            actualizarMetricas();
            anexarMovimiento(prod.getCodigo(), "Ingreso", cantidad, 0);
            return true;
        }

        public boolean registrarSalida(String codigo, int cantidad) {
            Producto prod = buscarProducto(codigo);
            if (prod == null || cantidad <= 0) {
                return false;
            }
            if (!prod.disminuirStock(cantidad)) {
                return false;
            }
            espacioOcupado -= cantidad;
            if (espacioOcupado < 0) {
                espacioOcupado = 0;
            }
            actualizarMetricas();
            anexarMovimiento(prod.getCodigo(), "Salida", 0, cantidad);
            return true;
        }

        public boolean realizarCompra(String codigo, int cantidad) {
            Producto prod = buscarProducto(codigo);
            if (prod == null || cantidad <= 0) {
                return false;
            }
            double totalCompra = prod.getPrecioCompra() * cantidad;
            if (!verificarPresupuesto(totalCompra) || !verificarEspacio(cantidad)) {
                return false;
            }
            if (!prod.aumentarStock(cantidad)) {
                return false;
            }
            presupuestoDisponible -= totalCompra;
            espacioOcupado += cantidad;
            actualizarMetricas();
            anexarMovimiento(prod.getCodigo(), "Compra", cantidad, 0);
            return true;
        }

        public void mostrarProductos() {
            if (productos.isEmpty()) {
                System.out.println("Inventario sin artículos no se puede.");
                return;
            }
            Iterator<Producto> it = productos.iterator();
            while (it.hasNext()) {
                System.out.println(it.next().mostrarInformacion());
            }
        }

        public void mostrarMovimientos() {
            if (movimientos.isEmpty()) {
                System.out.println("Sin movimientos.");
                return;
            }
            Iterator<MovimientoInventario> it = movimientos.iterator();
            while (it.hasNext()) {
                System.out.println(it.next().mostrarMovimiento());
            }
        }

        public void mostrarProductosBajoStock() {
            int contador = 0;
            for (int i = 0; i < productos.size(); i++) {
                Producto item = productos.get(i);
                if (item.reposicion()) {
                    System.out.println(item.mostrarInformacion());
                    contador++;
                }
            }
            if (contador == 0) {
                System.out.println("Todos los productos superan su stock .");
            }
        }

        public double calcularValorTotalInventario() {
            double acumulado = 0.0;
            for (int i = 0; i < productos.size(); i++) {
                acumulado += productos.get(i).calcularValorInventario();
            }
            this.costoTotalInventario = acumulado;
            return acumulado;
        }

        public boolean verificarPresupuesto(double costo) {
            return costo >= 0.0 && this.presupuestoDisponible >= costo;
        }

        public boolean verificarEspacio(double espacio) {
            return espacio >= 0.0 && (this.espacioOcupado + espacio) <= this.capacidadMaxima;
        }

        public List<Producto> getProductos() {
            return productos;
        }

        public List<MovimientoInventario> getMovimientos() {
            return movimientos;
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
