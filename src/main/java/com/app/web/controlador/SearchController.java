package com.app.web.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.app.web.entidad.Producto;
import com.app.web.servicio.ProductService;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

	private final ProductService productService;

	public SearchController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<List<Producto>> buscarProductos(@RequestParam("nombre") String searchTerm) {
		List<Producto> productos = productService.findByNombreOrCategoriaContainingIgnoreCase(searchTerm);
		return ResponseEntity.ok(productos);
	}
}
