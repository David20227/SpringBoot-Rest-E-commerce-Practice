package com.app.web.servicio;

import java.util.List;

import com.app.web.entidad.Producto;

public interface ProductService {

	public List<Producto> getAllProducts();

	public List<Producto> getRandProducts();

	public Producto getProductoById(Long Id);

	public List<Producto> findByNombreOrCategoriaContainingIgnoreCase(String nombre);
}
