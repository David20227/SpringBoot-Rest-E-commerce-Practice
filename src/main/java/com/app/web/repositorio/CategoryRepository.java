package com.app.web.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.web.entidad.Categoria;
import com.app.web.entidad.Producto;

public interface CategoryRepository extends JpaRepository<Categoria, Long> {

	Categoria findByNombre(String nombre);

	@Query("SELECT p FROM Producto p JOIN p.categorias c WHERE c.nombre = :nombreCategoria")
	List<Producto> findProductosByCategoria(@Param("nombreCategoria") String nombreCategoria);

}
