package com.ejemplo.ventas.controller;

import com.ejemplo.ventas.dto.VentaRequestDTO;
import com.ejemplo.ventas.entity.Producto;
import com.ejemplo.ventas.entity.Venta;
import com.ejemplo.ventas.service.ProductoService;
import com.ejemplo.ventas.service.VentaService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private VentaService ventaService;

    // Productos
    @GetMapping("/")
    public String index() {
        return "redirect:/productos";
    }

    @GetMapping("/productos")
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.listarTodos());
        return "productos/lista";
    }

    @GetMapping("/productos/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        return "productos/formulario";
    }

    @PostMapping("/productos")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.guardar(producto);
        return "redirect:/productos";
    }

    @GetMapping("/productos/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Producto producto = productoService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        model.addAttribute("producto", producto);
        return "productos/formulario";
    }

    @GetMapping("/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable Integer id) {
        productoService.eliminar(id);
        return "redirect:/productos";
    }

    // Ventas
    @GetMapping("/ventas")
    public String listarVentas(Model model) {
        model.addAttribute("ventas", ventaService.listarVentas());
        return "ventas/lista";
    }

    @GetMapping("/ventas/nueva")
    public String mostrarFormularioVenta(Model model) {
        model.addAttribute("productos", productoService.listarTodos());
        return "ventas/nueva";
    }

    @PostMapping("/ventas")
    public String registrarVenta(@RequestParam("productoId") List<Integer> productoIds,
                                 @RequestParam("cantidad") List<Integer> cantidades,
                                 Model model) {
        // Construir el DTO desde los parámetros del formulario
        VentaRequestDTO request = new VentaRequestDTO();
        List<VentaRequestDTO.ItemDTO> items = new ArrayList<>();
        for (int i = 0; i < productoIds.size(); i++) {
            VentaRequestDTO.ItemDTO item = new VentaRequestDTO.ItemDTO();
            item.setProductoId(productoIds.get(i));
            item.setCantidad(cantidades.get(i));
            items.add(item);
        }
        request.setItems(items);

        try {
            ventaService.registrarVenta(request);
            return "redirect:/ventas";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("productos", productoService.listarTodos());
            return "ventas/nueva";
        }
    }

    @GetMapping("/ventas/{id}")
    public String verVenta(@PathVariable Integer id, Model model) {
        Venta venta = ventaService.obtenerVentaConDetalles(id);
        model.addAttribute("venta", venta);
        return "ventas/ver";
    }
}