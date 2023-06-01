package com.app.web.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.web.entidad.Categoria;
import com.app.web.entidad.Producto;
import com.app.web.servicio.CategoryService;

import dto.CategoriaDto;
import dto.ProductoDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")
	@ResponseBody
	public ResponseEntity<List<CategoriaDto>> getAllCategories() {
		List<Categoria> categorias = categoryService.getAllCategories();
		List<CategoriaDto> categoriaDtos = convertToCategoriaDtoList(categorias);
		return ResponseEntity.ok(categoriaDtos);
	}

	@GetMapping("/{name}")
	@ResponseBody
	public ResponseEntity<List<ProductoDto>> getCategoryByName(@PathVariable("name") String name) {
		Categoria categoria = categoryService.getCategoryByName(name);

		if (categoria != null) {
			List<Producto> productosCategoria = categoryService.findProductosByCategoria(name);
			List<ProductoDto> productosCategoriaDto = convertToProductoDtoList(productosCategoria);
			return ResponseEntity.ok(productosCategoriaDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	private List<ProductoDto> convertToProductoDtoList(List<Producto> productos) {
		List<ProductoDto> productoDtos = new ArrayList<>();

		for (Producto producto : productos) {
			ProductoDto productoDto = new ProductoDto(producto.getId(), producto.getNombre(), producto.getPrecio(),
					producto.getImagen(), producto.getDescripcion(),
					convertToCategoriaDtoSet(producto.getCategorias()));

			productoDtos.add(productoDto);
		}

		return productoDtos;
	}

	private Set<CategoriaDto> convertToCategoriaDtoSet(Set<Categoria> categorias) {
		Set<CategoriaDto> categoriaDtos = new HashSet<>();

		for (Categoria categoria : categorias) {
			CategoriaDto categoriaDto = new CategoriaDto(categoria.getId(), categoria.getNombre(),
					convertToProductoDtoList(new ArrayList<>(categoria.getProductos())));

			categoriaDtos.add(categoriaDto);
		}

		return categoriaDtos;
	}

	private List<CategoriaDto> convertToCategoriaDtoList(List<Categoria> categorias) {
		List<CategoriaDto> categoriaDtos = new ArrayList<>();

		for (Categoria categoria : categorias) {
			CategoriaDto categoriaDto = new CategoriaDto(categoria.getId(), categoria.getNombre(),
					convertToProductoDtoList(new ArrayList<>(categoria.getProductos())));

			categoriaDtos.add(categoriaDto);
		}

		return categoriaDtos;
	}

}
