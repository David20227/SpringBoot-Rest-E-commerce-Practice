package com.app.web.servicio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.web.entidad.Categoria;
import com.app.web.entidad.Producto;

public interface CategoryService {

	public List<Categoria> getAllCategories();

	public Categoria getCategoryByName(String name);

	public List<Producto> findProductosByCategoria(@Param("nombreCategoria") String nombreCategoria);

}
