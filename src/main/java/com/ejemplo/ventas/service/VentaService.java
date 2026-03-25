package com.ejemplo.ventas.service;

import com.ejemplo.ventas.dto.VentaRequestDTO;
import com.ejemplo.ventas.entity.Producto;
import com.ejemplo.ventas.entity.ProductoVenta;
import com.ejemplo.ventas.entity.Venta;
import com.ejemplo.ventas.repository.ProductoRepository;
import com.ejemplo.ventas.repository.ProductoVentaRepository;
import com.ejemplo.ventas.repository.VentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ProductoVentaRepository productoVentaRepository;

    @Transactional
    public Venta registrarVenta(VentaRequestDTO request) {
        // Crear venta
        Venta venta = new Venta();
        venta.setTotalventa(0); // se calculará después
        venta = ventaRepository.save(venta);

        int total = 0;
        List<ProductoVenta> listaDetalle = new ArrayList<>();

        for (VentaRequestDTO.ItemDTO item : request.getItems()) {
            Producto producto = productoRepository.findById(item.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            // Verificar stock suficiente
            if (producto.getStock() < item.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            // Calcular subtotal y acumular
            int subtotal = producto.getCosto() * item.getCantidad();
            total += subtotal;

            // Crear detalle
            ProductoVenta detalle = new ProductoVenta(venta, producto, item.getCantidad());
            listaDetalle.add(detalle);

            // Actualizar stock
            producto.setStock(producto.getStock() - item.getCantidad());
            productoRepository.save(producto);
        }

        // Guardar todos los detalles
        productoVentaRepository.saveAll(listaDetalle);

        // Actualizar total de la venta
        venta.setTotalventa(total);
        return ventaRepository.save(venta);
    }

    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    public Venta obtenerVentaConDetalles(Integer id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        // Inicializar la lista para evitar LazyInitializationException en la vista
        venta.getProductosVenta().size();
        return venta;
    }
}