package dto;

import java.util.Set;

public class ProductoDto {
	private Long id;
	private String nombre;
	private float precio;
	private String imagen;
	private String descripcion;
	private Set<CategoriaDto> categorias;

	public ProductoDto() {
	}

	public ProductoDto(Long id, String nombre, float precio, String imagen, String descripcion,
			Set<CategoriaDto> categorias) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.imagen = imagen;
		this.descripcion = descripcion;
		this.categorias = categorias;
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

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<CategoriaDto> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<CategoriaDto> categorias) {
		this.categorias = categorias;
	}
}
