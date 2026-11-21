package Taller;
import java.time.LocalDate;
public class Main {
    public static void main(String[] args) {
        Inventario bodega = new Inventario(3500.0, 150.0);

        Producto mouse = new ProductoElectronico(
                    "EL-101",
                    "Mouse Inalámbrico Ergo",
                    "Electrónica",
                    14.5,
                    26.0,
                    20,
                    6,
                    LocalDate.of(2026, 11, 21),
                    "Distribuidora And",
                    "Logitech",
                    24
            );

            Producto adaptador = new ProductoAccesorio(
                    "AC-204",
                    "Adaptador HDMI a uno VGA",
                    "Accesorios",
                    5.0,
                    11.5,
                    4,
                    8,
                    LocalDate.of(2026, 11, 21),
                    "Importadora Nort",
                    "Adaptador de vid"
            );

            bodega.registrarProducto(mouse);
            bodega.registrarProducto(adaptador);

            System.out.println("Catalogo inicial");
            bodega.mostrarProductos();

            System.out.println("Alertas reposicion");
            bodega.mostrarProductosBajoStock();

            bodega.realizarCompra("AC-204", 12);
            bodega.registrarSalida("EL-101", 5);

            System.out.println("Catalogo actualizado");
            bodega.mostrarProductos();

            System.out.println("Historial movimientos");
            bodega.mostrarMovimientos();

            System.out.println("Saldo de presupuesto: " + bodega.getPresupuestoDisponible());
            System.out.println("Ocupación: " + bodega.getEspacioOcupado() + "/" + bodega.getCapacidadMaxima());
            System.out.println("Valor total: " + bodega.getCostoTotalInventario());
        }
    }
