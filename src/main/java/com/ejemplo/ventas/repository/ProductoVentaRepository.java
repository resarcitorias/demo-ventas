package com.ejemplo.ventas.repository;
import com.ejemplo.ventas.entity.ProductoVenta;
import com.ejemplo.ventas.entity.ProductoVentaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoVentaRepository extends JpaRepository<ProductoVenta, ProductoVentaId> {
}