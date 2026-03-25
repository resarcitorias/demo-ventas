package com.ejemplo.ventas.dto;

import java.util.List;

public class VentaRequestDTO {
    private List<ItemDTO> items;

    public static class ItemDTO {
        private Integer productoId;
        private Integer cantidad;
		public Integer getProductoId() {
			return productoId;
		}
		public void setProductoId(Integer productoId) {
			this.productoId = productoId;
		}
		public Integer getCantidad() {
			return cantidad;
		}
		public void setCantidad(Integer cantidad) {
			this.cantidad = cantidad;
		}
		@Override
		public String toString() {
			return "ItemDTO [productoId=" + productoId + ", cantidad=" + cantidad + "]";
		}

        
    }

	public List<ItemDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemDTO> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "VentaRequestDTO [items=" + items + "]";
	}
}