package com.ejemplo.ventas.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "PRODUCTOS")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPRODUCTOS")
    private Integer id;

    @Column(nullable = false, length = 45)
    private String nombre;

    @Column(nullable = false)
    private Integer costo;

    @Column(nullable = false)
    private Integer stock;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductoVenta> productoVentas;

    // Constructores, getters y setters
    public Producto() {}

    public Producto(String nombre, Integer costo, Integer stock) {
        this.nombre = nombre;
        this.costo = costo;
        this.stock = stock;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCosto() {
		return costo;
	}

	public void setCosto(Integer costo) {
		this.costo = costo;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public List<ProductoVenta> getProductoVentas() {
		return productoVentas;
	}

	public void setProductoVentas(List<ProductoVenta> productoVentas) {
		this.productoVentas = productoVentas;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", costo=" + costo + ", stock=" + stock
				+ ", productoVentas=" + productoVentas + "]";
	}

}