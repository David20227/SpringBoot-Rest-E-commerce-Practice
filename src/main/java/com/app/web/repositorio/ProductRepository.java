package com.app.web.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.web.entidad.Producto;

public interface ProductRepository extends JpaRepository<Producto, Long> {

	@Query(value = "SELECT * FROM producto ORDER BY RAND() LIMIT 10", nativeQuery = true)
	List<Producto> getRandProducts();

	@Query("SELECT DISTINCT p FROM Producto p JOIN p.categorias c WHERE p.nombre LIKE %:nombre% OR c.nombre LIKE %:nombre%")
	List<Producto> findByNombreOrCategoriaContainingIgnoreCase(String nombre);

}
