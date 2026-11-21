package Taller;
import java.time.LocalDate;
public class Main {
    static void main() {
        Inventario inventario = new Inventario(4000.0, 150.0);

        Producto teclado = new ProductoElectronico(
                "P01",
                "Teclado Mecanico",
                "Electronico",
                30.0,
                50.0,
                10,
                4,
                LocalDate.of(2026, 11, 21),
                "Proveedor A",
                "Redragon",
                12
        );

        Producto cable = new ProductoAccesorio(
                "P02",
                "Cable USB",
                "Accesorio",
                5.0,
                10.0,
                3,
                6,
                LocalDate.of(2026, 11, 21),
                "Proveedor B",
                "Cable"
        );

        boolean reg1 = inventario.registrarProducto(teclado);
        boolean reg2 = inventario.registrarProducto(cable);
        System.out.println("Registros: " + reg1 + ", " + reg2);

        System.out.println("Productos registrados:");
        inventario.mostrarProductos();

        System.out.println("Bajo stock:");
        inventario.mostrarProductosBajoStock();

        boolean compraOk = inventario.realizarCompra("P02", 8);
        boolean entradaOk = inventario.registrarEntrada("P01", 2);
        boolean salidaOk = inventario.registrarSalida("P01", 4);
        boolean elimOk = inventario.eliminarProducto("P02");

        System.out.println("Compra: " + compraOk + " | Entrada: " + entradaOk + " | Salida: " + salidaOk + " | Eliminar P02: " + elimOk);

        System.out.println("Los productos finales:");
        inventario.mostrarProductos();

        System.out.println("Movimientos:");
        inventario.mostrarMovimientos();

        System.out.println("Presupuesto disponible: " + inventario.getPresupuestoDisponible());
        System.out.println("El espacio esta ocupado: " + inventario.getEspacioOcupado() + "/" + inventario.getCapacidadMaxima());
        System.out.println("Costo total del inventario: " + inventario.getCostoTotalInventario());
    }
}
