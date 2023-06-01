package com.app.web.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.app.web.entidad.Categoria;
import com.app.web.entidad.Producto;
import com.app.web.repositorio.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository category;

	@Override
	public List<Categoria> getAllCategories() {
		return category.findAll();

	}

	@Override
	public Categoria getCategoryByName(String name) {

		return category.findByNombre(name);
	}

	@Override
	public List<Producto> findProductosByCategoria(@Param("nombreCategoria") String nombreCategoria) {

		return category.findProductosByCategoria(nombreCategoria);
	}

}
