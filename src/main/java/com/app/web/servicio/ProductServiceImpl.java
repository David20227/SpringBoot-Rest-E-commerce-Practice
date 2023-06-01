package com.app.web.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.web.repositorio.ProductRepository;
import com.app.web.entidad.Producto;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository producto;

	@Override
	public List<Producto> getAllProducts() {

		return producto.findAll();

	}

	@Override
	public List<Producto> getRandProducts() {

		return producto.getRandProducts();

	}

	@Override
	public Producto getProductoById(Long Id) {

		return producto.findById(Id).get();
	}

	@Override
	public List<Producto> findByNombreOrCategoriaContainingIgnoreCase(String nombre) {

		return producto.findByNombreOrCategoriaContainingIgnoreCase(nombre);

	}

}
