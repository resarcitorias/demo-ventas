package com.ejemplo.ventas.controller;

import com.ejemplo.ventas.dto.VentaRequestDTO;
import com.ejemplo.ventas.entity.Venta;
import com.ejemplo.ventas.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @GetMapping
    public List<Venta> listar() {
        return ventaService.listarVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(ventaService.obtenerVentaConDetalles(id));
    }

    @PostMapping
    public ResponseEntity<Venta> registrar(@RequestBody VentaRequestDTO request) {
        Venta nueva = ventaService.registrarVenta(request);
        return ResponseEntity.ok(nueva);
    }
}