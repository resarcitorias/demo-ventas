package com.ejemplo.ventas.entity;
import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProductoVentaId implements Serializable {
    @Column(name = "VENTA_idVENTA")
    private Integer ventaId;

    @Column(name = "PRODUCTOS_idPRODUCTOS")
    private Integer productoId;

	public ProductoVentaId() {
	}
	public ProductoVentaId(Integer ventaId, Integer productoId) {
		super();
		this.ventaId = ventaId;
		this.productoId = productoId;
	}

	public Integer getVentaId() {
		return ventaId;
	}

	public void setVentaId(Integer ventaId) {
		this.ventaId = ventaId;
	}

	public Integer getProductoId() {
		return productoId;
	}

	public void setProductoId(Integer productoId) {
		this.productoId = productoId;
	}

	@Override
	public String toString() {
		return "ProductoVentaId [ventaId=" + ventaId + ", productoId=" + productoId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(productoId, ventaId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductoVentaId other = (ProductoVentaId) obj;
		return Objects.equals(productoId, other.productoId) && Objects.equals(ventaId, other.ventaId);
	}
}