package com.ejemplo.ventas.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "VENTA")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVENTA")
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private Integer totalventa;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductoVenta> productosVenta;

    // Constructores, getters y setters
    public Venta() {
        this.fecha = LocalDateTime.now();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Integer getTotalventa() {
		return totalventa;
	}

	public void setTotalventa(Integer totalventa) {
		this.totalventa = totalventa;
	}

	public List<ProductoVenta> getProductosVenta() {
		return productosVenta;
	}

	public void setProductosVenta(List<ProductoVenta> productosVenta) {
		this.productosVenta = productosVenta;
	}

	@Override
	public String toString() {
		return "Venta [id=" + id + ", fecha=" + fecha + ", totalventa=" + totalventa + ", productosVenta="
				+ productosVenta + "]";
	}

}