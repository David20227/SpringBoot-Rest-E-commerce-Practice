package com.app.web.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.web.servicio.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService product;

	@GetMapping({ "/products", "/" })
	public String getProducts(Model modelo) {
		modelo.addAttribute("productos", product.getRandProducts());
		return "products";
	}

	@GetMapping("/products/{id}")
	public String showProduct(@PathVariable Long id, Model model) {
		model.addAttribute("producto", product.getProductoById(id));
		return "productoById";
	}

}
