package dto;

import java.util.List;

public class CategoriaDto {
	private Long id;
	private String nombre;
	private List<ProductoDto> productos;

	public CategoriaDto() {
	}

	public CategoriaDto(Long id, String nombre, List<ProductoDto> productos) {
		this.id = id;
		this.nombre = nombre;
		this.productos = productos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ProductoDto> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoDto> productos) {
		this.productos = productos;
	}
}
