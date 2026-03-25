package com.ejemplo.ventas.entity;
import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Table(name = "PRODUCTOS_VENTA")
public class ProductoVenta {
    @EmbeddedId
    private ProductoVentaId id;

    @ManyToOne
    @MapsId("ventaId")
    @JoinColumn(name = "VENTA_idVENTA")
    private Venta venta;

    @ManyToOne
    @MapsId("productoId")
    @JoinColumn(name = "PRODUCTOS_idPRODUCTOS")
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad;

    // Constructores, getters y setters
    public ProductoVenta() {}

    public ProductoVenta(Venta venta, Producto producto, Integer cantidad) {
        this.venta = venta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.id = new ProductoVentaId(venta.getId(), producto.getId());
    }

	public ProductoVentaId getId() {
		return id;
	}

	public void setId(ProductoVentaId id) {
		this.id = id;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "ProductoVenta [id=" + id + ", venta=" + venta + ", producto=" + producto + ", cantidad=" + cantidad
				+ "]";
	}

}