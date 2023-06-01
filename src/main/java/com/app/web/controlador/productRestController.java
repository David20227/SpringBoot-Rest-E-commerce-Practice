package com.app.web.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.app.web.entidad.Producto;
import com.app.web.servicio.ProductService;

@RestController
@RequestMapping("productRest")
public class productRestController {

	@Autowired
	ProductService productoService;

	@GetMapping("/{id}")
	public ResponseEntity<Producto> getProductById(@PathVariable("id") Long Id) {
		Producto producto = productoService.getProductoById(Id);
		return ResponseEntity.ok(producto);
	}
}
